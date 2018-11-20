/*
*Angel Roberto Ruiz Mendoza A01324489
*Carlos Augusto Amador Manilla A01329447
*Clase que simula ser el sistema de colas
*/
import java.util.concurrent.TimeUnit;
import java.util.*;

public class SystemQ{

    private int nc;
    private int ns;
    private int A;
    private int S;
    private RandomGen random;
    private int time;
    private ArrayList<Client> queue;
    private ArrayList<Server> servers;
    private ArrayList<Server> busyServers;
    public SystemQ(int nc, int ns, int A, int S, RandomGen random){
        this.nc = nc;
        this.ns = ns;
        double qA = 60.0 / (A);
        double qS = 60.0 / (S);
        this.A = (int) Math.ceil(qA);
        this.S = (int) Math.ceil(qS);
        System.out.println((this.A) + " " + (this.S));
        this.time = 0;
        this.random = random;
    }

    public void run(){
        queue = new ArrayList<Client>();
        servers = new ArrayList<Server>();
        busyServers = new ArrayList<Server>();
        Client nC;
        for(int i = 0; i < ns; i++){
            servers.add(new Server(S, i, random));
        }
        nC = new Client(0, A);
        int i = 1;
        while(nC != null || !queue.isEmpty() || !busyServers.isEmpty()){
            //System.out.println("<" + time + ">");

            //Libera los servidores que terminarón de servir
            ArrayList<Server> busy = getFinishedBusy();
            if(busy.size() > 0){
                for(Server s : busy){
                    printTermina(s.attending, s);
                }
                removeAndAdd(busy);
            }

            //Mete a un nuevo cliente a la cola
            if(nC != null && nC.arrivalTime == time){
                queue.add(nC);
                printLlega(nC);
                if(i <= nc){
                    nC = new Client(i, time, A, random);
                    i++;
                }
                else{
                    nC = null;
                }
            }

            //Atiende clientes de la fila 
            while(!queue.isEmpty() && !servers.isEmpty()){
                Client first = queue.remove(0);
                Server firstServer = servers.remove(0);
                firstServer.attend(first, time);
                printAtendido(first, firstServer);
                busyServers.add(firstServer);
            }
            if(!queue.isEmpty() && servers.isEmpty()){
                for(Client c : queue){
                    printEspera(c);
                }
                
            }
            
            waitSec(1);
            time++;
        }
    }

    private void removeAndAdd(ArrayList<Server> list){
        for(Server s : list){
            for(int i = 0; i < busyServers.size(); i++){
                if(s.id == busyServers.get(i).id){
                    busyServers.remove(i);
                }
            }
            servers.add(s);
        }
    }

    private ArrayList<Server> getFinishedBusy(){
        ArrayList<Server> list = new ArrayList<Server>();
        for(Server s : busyServers){
            if(s.finishedAttending(time)){
                list.add(s);
            }
        }
        return list;
    }

    private void printLlega(Client c){
        System.out.println("<" + time + ">: Llega cliente " + c.id);
    }

    private void printAtendido(Client c, Server s){
        System.out.println("<" + time + ">: Cliente " + c.id + " es atendido por servidor " + s.id);
    }

    private void printEspera(Client c){
        System.out.println("<" + time + ">: Cliente " + c.id + " esperando a que un servidor se desocupe");
    }

    private void printTermina(Client c, Server s){
        System.out.println("<" + time + ">: Cliente " + c.id + " termino de ser atendido por servidor " + s.id);
    }

    public int minToSecond(double x){
        int y = (int) x;
        x = x - y;
        return (int) (y * 60);
    }

    public void waitMins(int x){
        try{
            TimeUnit.MINUTES.sleep(x);
        }
        catch(Exception e){}    }

    public void waitSec(int x){
        try{
            TimeUnit.SECONDS.sleep(x);
        }
        catch(Exception e){}
        
    }
}
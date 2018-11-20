/*
*Angel Roberto Ruiz Mendoza A01324489
*Carlos Augusto Amador Manilla A01329447
*Clase que simula ser un  servidor que atiende un cliente 
*/
public class Server{
    private int S;
    public int id;
    public boolean available;
    public Client attending;
    public int finishTime;
    private RandomGen random;

    //Constructor de la clase
    public Server(int S, int id, RandomGen random){
        this.S = S;
        this.id = id;
        available = true;
        attending = null;
        finishTime = 0;
        this.random = random;
    }

    //Metodod que recibe un cliente y lo atiende
    public void attend(Client attending, int time){
        available = false;
        this.attending = attending;
        calculateFinishTime(time);
    }

    //Metodo que clacula cuando terminará de atender al cliente
    private void calculateFinishTime(int time){
        double ran = random.nextDouble();
        ran -= 0.5;
        int suma = (int) Math.ceil(S * ran);
        finishTime = time + S + suma;
    }

    //Metodo que simula ser el termino de la atencion al cliente
    public boolean finishedAttending(int time){
        if (time == finishTime){
            available = true;
        }
        return available;
    }

}
/*
*Angel Roberto Ruiz Mendoza A01324489
*Carlos Augusto Amador Manilla A01329447
*Clase que simula ser un cliente
*/
public class Client{
    public int id;
    public int A;
    public int arrivalTime;
    private RandomGen random;

    //Constructor para tiempo 0
    public Client(int id, int A){
        this.id = id;
        this.arrivalTime = 0;
        this.A = A;
        
    }

    //Constructor que genera un tiempo de llegada aleatorio
    public Client(int id, int time, int A, RandomGen random){
        this.id = id;
        this.A = A;
        this.random = random;
        calculateArrivalTime(time, A);
    }

    //Metodo que calcula el tiempo de llegada
    private void calculateArrivalTime(int time, int A){
        double ran = random.nextDouble();
        ran -= 0.5;
        int suma = (int) Math.ceil(A * ran);
        this.arrivalTime = time + A + suma;
    }
}
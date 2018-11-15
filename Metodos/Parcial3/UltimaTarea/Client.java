public class Client{
    public int id;
    public int A;
    public int arrivalTime;
    private RandomGen random;

    public Client(int id, int A){
        this.id = id + 1;
        this.arrivalTime = 0;
        this.A = A;
        
    }

    public Client(int id, int time, int A, RandomGen random){
        this.id = id;
        this.A = A;
        this.random = random;
        calculateArrivalTime(time, A);
    }

    private void calculateArrivalTime(int time, int A){
        double ran = random.getRandomDouble();
        ran -= 0.5;
        this.arrivalTime = time + A + (int)(A * ran);
    }
}
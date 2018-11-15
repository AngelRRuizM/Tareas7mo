
public class Server{
    private int S;
    public int id;
    public boolean available;
    public Client attending;
    public int finishTime;
    private RandomGen random;

    public Server(int S, int id, RandomGen random){
        this.S = S;
        this.id = id + 1;
        available = true;
        attending = null;
        finishTime = 0;
        this.random = random;
    }

    public void attend(Client attending, int time){
        available = false;
        this.attending = attending;
        calculateFinishTime(time);
    }

    private void calculateFinishTime(int time){
        double ran = random.getRandomDouble();
        ran -= 0.5;
        finishTime = time + S + (int)(S * ran);
    }

    public boolean finishedAttending(int time){
        if (time == finishTime){
            available = true;
        }
        return available;
    }

}
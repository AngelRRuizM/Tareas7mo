import java.util.*;

public class Main{
    public static void main(String[] args) {
        int x0, a, c, m, nc, ns;
        int A, S;
        Scanner sc = new Scanner(System.in);
        x0 = sc.nextInt();
        a = sc.nextInt();
        c = sc.nextInt();
        m = sc.nextInt();
        nc = sc.nextInt();
        ns = sc.nextInt();
        A = sc.nextInt();
        S = sc.nextInt();
        if(A >= S * ns){
            System.out.println("Sistema imposible");
            return;
        }
        RandomGen random = new RandomGen(a, c, m);
        random.generateList(x0);
        
        SystemQ system = new SystemQ(nc, ns, A, S, random);
        system.run();
    }
}
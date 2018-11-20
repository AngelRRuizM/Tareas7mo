/*
*Angel Roberto Ruiz Mendoza A01324489
*Carlos Augusto Amador Manilla A01329447
*Clase que saca numeros aleatorimente 
*/
import java.util.*;

public class RandomGen{

    private int a;
    private int c;
    private int m;
    private int xi;
    public int maxRan;
    //Constructor con corrimiento
    public RandomGen(int a, int c, int m, int x0){
        this.a = a;
        this.c = c;
        this.m = m;
        this.xi = x0;
        this.maxRan = m;
    }

    //Constructor sin corrimiento
    public RandomGen(int a, int m, int x0){
        this.a = a;
        this.c = 0;
        this.m = m;
        this.xi = x0;
        this.maxRan = m - 1;
    }

    //Metodo que retorna un double del 0 a x
    public double nextDouble(){
        double ran = nextInt() * 1.0;
        ran = ran / ((maxRan - 1) * 1.0);
        return ran;
    }

    //Metodo que retorna un enetero de [0, x)
    public int nextInt(){
        xi = ((xi * a) + c) % m;
        return xi;
    }

}
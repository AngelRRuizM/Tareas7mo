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
    private int n;
    public int maxRan;
    private ArrayList<Integer> generated;
    private int index;

    //Constructor con corrimiento
    public RandomGen(int a, int c, int m){
        this.a = a;
        this.c = c;
        this.m = m;
        this.maxRan = m;
    }

    //Constructor sin corrimiento
    public RandomGen(int a, int m){
        this.a = a;
        this.c = 0;
        this.m = m;
        this.maxRan = m - 1;
    }

    //Metodo que genera la lista de numeros
    public void generateList(int xi){
        generated = new ArrayList<Integer>();
        generated.add(xi);
        //Se calcula la lista de nuemros aleatorios con la formula
        int nxi = ((((xi % m) * (a % m)) % m) + (c % m)) % m;
        generated.add(nxi);
        while(true){
            nxi = ((((nxi % m) * (a % m)) % m) + (c % m)) % m;
            if(nxi == xi){
                break;
            }
            generated.add(nxi);
            //System.out.println(generated.get(generated.size() - 1));
        }
        index = xi % generated.size();
    }

    //Metodo que retorna un double del 0 a x
    public double getRandomDouble(){
        double ran = generated.get(index % generated.size()) * 1.0;
        ran = ran / (maxRan * 1.0);
        index++;
        return ran;
    }

    //Metodo que retorna un enetero de [0, x)
    public int getRandomInt(int n){
        double ran = getRandomDouble();
        ran = ran * n;
        return (int)ran;
    }

}
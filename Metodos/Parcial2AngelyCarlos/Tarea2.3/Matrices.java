import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Matrices{

    private int size;
    private Fraction[] fixedPoint;
    private Fraction[][] originalMatrix;
    private ArrayList<Fraction[][]> powList;
    private String[] variables;

    public Matrices(Fraction[][] mat, int s){
        size = s;
        fixedPoint = new Fraction[s + 1];
        originalMatrix = mat;
        powList = new ArrayList<Fraction [][]>();
        powList.add(originalMatrix);
    }

    public Fraction[][] getOriginal(){
        return originalMatrix;
    }

    public ArrayList<Fraction[][]> getList(){
        return powList;
    }

    public Fraction[] getFixed(){
        return fixedPoint;
    }

    public String[] getVariables(){
        return variables;
    }
    
    public void pow(int p){
        for(int i = 1; i < p; i++){

            //System.out.println("Mat in " + i);
            Fraction[][] mult = mmult(originalMatrix, powList.get(powList.size() - 1));
            //printm(mult);
            powList.add(mult);
        }
    }

    public Fraction[][] mmult(Fraction[][] x, Fraction[][] y){
        //System.out.println("mmult");
        Fraction[][] mmult = new Fraction[size][size];
        for(int i = 0; i < size; i++){
            //System.out.println("i " + i);


            //System.out.println("For 3");
            //System.out.println("For 2");
            for(int j = 0; j < size; j++){

            //System.out.println("j " + j);
                Fraction acum = new Fraction(0);

                for(int k = 0; k < size; k++){

                    //System.out.println("k " + k);
                    Fraction mult = new Fraction(x[i][k].num.toString(), x[i][k].den.toString());
                    mult.mult(y[k][j]);
                    acum.add(mult);
                    
                }
                mmult[i][j] = acum;
            }
        }

        //System.out.println("Finshed mmult");
        //printm(mmult);
        return mmult;
    }

    public void printm(Fraction[][] m){
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                System.out.print(m[i][j].num.toString() + "/" + m[i][j].den.toString() + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void fixedPointVector(){
        variables = new String[size];

        getVariables(variables);

        Fraction[][] mat = copyMatrix(originalMatrix);
        long i = 2;
        mat = mmult(mat, mat);
        boolean converges = checkConverge(mat);
        while(i <= 2048 && !converges){
            System.out.println("i: " + i);
            mat = mmult(mat, mat);
            i *= 2;
            converges = checkConverge(mat);
        }

        fixedPoint = new Fraction[size];

        for(int j = 0; j < size; j++){
            fixedPoint[j] = new Fraction(mat[0][j]);
        }

    } 

    public boolean checkConverge(Fraction[][] mat){
        for(int j = 0; j < size; j++){
            for(int i = 1; i < size; i++){
                BigDecimal x = new BigDecimal(mat[i][j].num.toString() + ".0");
                x = x.divide(new BigDecimal(mat[i][j].den.toString() + ".0"), 5, BigDecimal.ROUND_FLOOR);
                BigDecimal y = new BigDecimal(mat[i - 1][j].num.toString());
                y = y.divide(new BigDecimal(mat[i - 1][j].den.toString()), 5, BigDecimal.ROUND_FLOOR);

                BigDecimal error = x.subtract(y);
                error = error.divide(y, 5, BigDecimal.ROUND_FLOOR);
                error = error.abs();
                if(error.compareTo(new BigDecimal(".001")) > 0){
                    return false;
                }
            }
        }
        return true;
    }

    public void getVariables(String[] vari){
        int x = -2;
        for(int i = 0; i < size; i++){
            if(i % 27 == 0){
                x++;
            }
            if(x == -1){
                char c = (char)('a' + i);
                vari[i] = "" + c;
            }
            else{
                char c1 = (char)('a' + x);
                char c2 = (char)('a' + i);
                vari[i] = "" + c1 + c2;
            }
        }
    }

    public Fraction[][] copyMatrix(Fraction[][] x){
        //System.out.println("Copy Matrix");
        Fraction[][] newM = new Fraction[x.length][x[0].length];
        for(int i = 0; i < x.length; i++){
            for(int j = 0; j < x[0].length; j++){
                newM[i][j] = new Fraction(x[i][j]);
            }
        }
        return newM;
    }

}
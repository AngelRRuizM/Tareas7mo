// Tarea 2.3 20/10/18
// Carlos Augusto Amador Manilla A01329447
// Angel Roberto Ruiz Mendoza A01325243

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
    
    // Calculates the power matrices
    public void pow(int p){
        for(int i = 1; i < p; i++){
            Fraction[][] mult = mmult(originalMatrix, powList.get(powList.size() - 1));
            powList.add(mult);
        }
    }

    // Matrix multiplication
    public Fraction[][] mmult(Fraction[][] x, Fraction[][] y){
        Fraction[][] mmult = new Fraction[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                Fraction acum = new Fraction(0);

                for(int k = 0; k < size; k++){
                    Fraction mult = new Fraction(x[i][k].num.toString(), x[i][k].den.toString());
                    mult.mult(y[k][j]);
                    acum.add(mult);
                }

                mmult[i][j] = acum;
            }
        }

        return mmult;
    }

    // Calculates fixed point vector using Gaussian elimination
    public void fixedPointVector(){
        variables = new String[size];
        getVariables(variables);
        
        Fraction[][] mat = getGJMatrix();
        fixedPoint = new Fraction[size];
        gauss(mat);

        getLastFixed();
    } 

    public void getLastFixed(){
        Fraction last = new Fraction(1);

        for(int i = 0; i < fixedPoint.length - 1; i++){
            last.subs(fixedPoint[i]);
        }

        fixedPoint[fixedPoint.length - 1] = last;
    }

    // Do gaussian elimination to the given matrix
    public void gauss(Fraction[][] a){
        int sing = forwardElim(a);

        if(sing != -1){
            System.out.println("Singular matrix. Can't apply gaussian elimination.");
            return;
        }

        backSub(a);
    }

    public void swapR(Fraction[][] mat, int i, int j){
        int N = size - 1;
        for(int k = 0; k <= N; k++){
            Fraction temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }

    // Forward elimination step for Gaussian elimination
    public int forwardElim(Fraction[][] mat){
        int N = size - 1;
        for(int k = 0; k < N; k++){
            int imax = k;
            Fraction vmax = new Fraction(0);

            for(int i = k + 1; i < N; i++){
                if(mat[i][k].absFraction().compareTo(vmax) == 1){
                    vmax = new Fraction(mat[i][k]);
                    imax = i;
                }
            }

            if(mat[k][imax].compareTo(new Fraction(0)) == 0){
                return k;
            }

            if(imax != k){
                swapR(mat, k, imax);
            }

            for(int i = k + 1; i < N; i++){
                Fraction f = new Fraction(mat[i][k]);
                f.div(mat[k][k]);

                for(int j = k + 1; j <= N; j++){
                    Fraction mult = new Fraction(mat[k][j]);
                    mult.mult(f);
                    mat[i][j].subs(mult);
                }

                mat[i][k] = new Fraction(0);

            }
            
        }
        return -1;
    }

    // Backward substitution step for Gaussian elimination
    public void backSub(Fraction[][] mat){
        int N = size - 1;
        for(int i = N - 1; i >= 0; i--){
            fixedPoint[i] = new Fraction(mat[i][N]);

            for(int j = i + 1; j < N; j++){
                Fraction mult = new Fraction(mat[i][j]);
                mult.mult(fixedPoint[j]);
                fixedPoint[i].subs(mult);
            }

            fixedPoint[i].div(mat[i][i]);
        }
    }

    // Formats the original matrix to a matrix which Gaussian elimination can be applied
    public Fraction[][] getGJMatrix(){
        Fraction[][] mat = new Fraction[size - 1][size];

        for(int i = 0; i < size -1; i++){
            for(int j = 0; j < size - 1; j++){
                mat[i][j] = new Fraction(originalMatrix[j][i]);
                mat[i][j].subs(originalMatrix[size - 1][i]);
            }
        }
        for(int i = 0; i < size - 1; i++){
            mat[i][i].subs(new Fraction(1));
        }
        for(int i = 0; i < size - 1; i++){
            mat[i][size - 1] = new Fraction(originalMatrix[size - 1][i]);
            mat[i][size - 1].mult(-1);
        }

        return mat;
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
        Fraction[][] newM = new Fraction[x.length][x[0].length];
        for(int i = 0; i < x.length; i++){
            for(int j = 0; j < x[0].length; j++){
                newM[i][j] = new Fraction(x[i][j]);
            }
        }
        return newM;
    }

}
import java.util.ArrayList;

public class Matrices{

    private int size;
    private Fraction[] fixedPoint;
    private Fraction[][] originalMatrix;
    private ArrayList<Fraction[][]> powList;

    public Matrices(Fraction[][] mat, int s){
        size = s;
        fixedPoint = new Fraction[s];
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
    
    public void pow(int p){
        for(int i = 1; i < p; i++){
            powList.add(mmult(originalMatrix, powList.get(powList.size() - 1)));
        }
    }

    public Fraction[][] mmult(Fraction[][] x, Fraction[][] y){
        //System.out.println("mmult");
        Fraction[][] mmult = new Fraction[size][size];
        for(int i = 0; i < size; i++){
            //System.out.println("i " + i);
            for(int j = 0; j < size; j++){

            //System.out.println("j " + j);
                Fraction acum = new Fraction(0);

                for(int k = 0; k < size; k++){

                    //System.out.println("k " + k);
                    Fraction mult = new Fraction(x[i][k].num, x[i][k].den);
                    mult.mult(y[k][j]);
                    acum.add(mult);
                    
                }
                mmult[i][j] = acum;
            }
        }

        //System.out.println("Finshed mmult");
        printm(mmult);
        return mmult;
    }

    public void printm(Fraction[][] m){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(m[i][j].num + "/" + m[i][j].den + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void fixedPointVector(){
        Fraction[][] matrix = copyMatrix(originalMatrix);
        Fraction[][] lastM;
        boolean converged = false;
        if(checkAbsorbent()){
            //System.out.println("absorbent");
            fixedPoint = null;
        }
        else{
            if(checkCycle()){

                //System.out.println("cycle");
                fixedPoint = null;
            }
            else{
                long i = 2;
                matrix = mmult(matrix, matrix);
                while(i <= 1048576){
                    printm(matrix);
                    matrix = mmult(matrix, originalMatrix);
                    i++;
                    if(converge(matrix)){
                        //System.out.println("converged");
                        for(int j = 0; j < size; j++){
                            fixedPoint[j] = matrix[j][j];
                        }
                        converged = true;
                        break;
                    }
                    //System.out.println("Didnt converged");
                }
                if(!converged){
                    fixedPoint=null;
                }
            }
        }
    } 

    public boolean converge(Fraction[][] currentM){
        //System.out.println("Check converge");
        Fraction dif;
        boolean converge = true;
        for(int j = 0; j < size; j++){
            for(int i = 1; i < size; i++){
                dif = new Fraction(currentM[i][j].num, currentM[i][j].den);
                dif.subs(currentM[i - 1][j]);
                dif.div(currentM[i][j]);
                double error = ((double)dif.num)/((double)dif.den);
                if(Math.abs(error) > .05){
                    converge = false;
                }
            }
        }
        return converge;
    }

    public Fraction[][] copyMatrix(Fraction[][] x){
        //System.out.println("Copy Matrix");
        Fraction[][] newM = new Fraction[x.length][x[0].length];
        for(int i = 0; i < x.length; i++){
            for(int j = 0; j < x[0].length; j++){
                newM[i][j] = new Fraction(x[i][j].num, x[i][j].den);
            }
        }
        return newM;
    }

    public boolean checkAbsorbent(){
        for(int i = 0; i < size; i++){
            if(originalMatrix[i][i].num == originalMatrix[i][i].den){
                return true;
            }
        }
        return false;
    }

    public boolean checkCycle(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(originalMatrix[i][j].num == originalMatrix[i][i].den){
                    if(hasCycle(j, i, j)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean hasCycle(int nextRow, int orI, int orJ){
        for(int i = 0; i < size; i++){
            if(originalMatrix[nextRow][i].num == originalMatrix[nextRow][i].den){
                if(nextRow == orI && i == orJ){
                    return true;
                }
                else{
                    return hasCycle(i, orI, orJ);
                }
            }
        }
        return false;
    }
}
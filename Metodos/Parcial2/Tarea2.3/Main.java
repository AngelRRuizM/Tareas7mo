import java.util.*;

public class Main{
    public static void main(String[] args) {
        int n, p;
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        Fraction [][] matrix = new Fraction [n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = new Fraction(sc.nextInt(), sc.nextInt());
            }
        }
        p = sc.nextInt();
        Matrices matrixList = new Matrices(matrix, n);
        matrixList.pow(p);
        matrixList.fixedPointVector();

        printMatrixList(matrixList.getList());
        printFixedPointVector(matrixList.getFixed());
    }

    public static void printMatrixList(ArrayList<Fraction[][]> list){
        System.out.println("Matrices");
        for(Fraction[][] m : list){
            for(int i = 0; i < m.length; i++){
                for(int j = 0; j < m.length; j++){
                    System.out.print(m[i][j].num + "/" +m[i][j].den + "   ");
                }
                System.out.println();
            }
        }
    }

    public static void printFixedPointVector(Fraction[] fixed){
        System.out.println("The fixed point vector is: ");
        for(int i = 0; i < fixed.length; i++){
            System.out.println(fixed[i].num + "/"+fixed[i].den);
        }
    }
}
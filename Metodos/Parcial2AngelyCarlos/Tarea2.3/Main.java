// Tarea 2.3 20/10/18
// Carlos Augusto Amador Manilla A01329447
// Angel Roberto Ruiz Mendoza A01325243

import java.util.*;

public class Main{
    public static void main(String[] args) {
        int n, p;
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        sc.nextLine();
        Fraction [][] matrix = new Fraction [n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                String x = sc.nextLine();
                String y = sc.nextLine();
                matrix[i][j] = new Fraction(x, y);
            }
        }
        p = sc.nextInt();
        
        Matrices matrixList = new Matrices(matrix, n);
        matrixList.pow(p);
        matrixList.fixedPointVector();

        printMatrixList(matrixList.getList());
        printFixedPointVector(matrixList.getFixed());
    }

    // Prints the matrices using the length of the biggest number to format all numbers
    public static void printMatrixList(ArrayList<Fraction[][]> list){
        Fraction[][] m;
        int maxLength = getMaxNumberLength(list);
        
        for(int k=0; k<list.size(); k++){
            System.out.printf("P%d\n", k+1);
            m = list.get(k);

            for(int i = 0; i < m.length; i++){
                for(int j = 0; j < m[0].length; j++){
                    System.out.printf("%" + maxLength + "s/%" + maxLength + "s    " , m[i][j].num.toString(), m[i][j].den.toString());
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    // Gets the length of the biggest number
    public static int getMaxNumberLength(ArrayList<Fraction[][]> list) {
        Fraction f;
        int max = Integer.MIN_VALUE;
        int current;

        for(Fraction[][] m: list) {
            for(int i=0; i<m.length; i++) {
                for(int j=0; j<m[0].length; j++) {
                    f = m[i][j];
                    current = Math.max(f.num.toString().length(), f.den.toString().length());

                    if(current > max) {
                        max = current;
                    }
                }
            }
        }

        return max;
    }

    public static void printFixedPointVector(Fraction[] fixed){
        System.out.print("Vector de punto fijo: (");

        for(int i = 0; i < fixed.length-1; i++){
            System.out.printf("%s/%s, " , fixed[i].num.toString(), fixed[i].den.toString());
        }

        System.out.printf("%s/%s)\n" , fixed[fixed.length-1].num.toString(), fixed[fixed.length-1].den.toString());
    }
}
package Algorithm;

import Equation.LinearEquation;
import Model.Matrix;

public class Seidel {
   public static double[][] seidelMethod(Matrix matrix, double epsilon) {
       rearrangeMatrix(matrix);
       System.out.println("Matrix after rearrange: ");
       matrix.printMatrix();
       int dimension = matrix.getDimension();
       Matrix copyMatrix = matrix.cloneMatrix();
       copyMatrix.toTriangular(0);
       System.out.println("Triangular matrix: ");
       copyMatrix.printMatrix();
       if(copyMatrix.getDeterminate() == 0) {
           return null;
       } else {
           System.out.println("Determinate of matrix: " + copyMatrix.getDeterminate());
       }
       double[][] C = new double[dimension][dimension];
       double[] D = new double[dimension];
       double[][] matrix_1 = matrix.getMatrix();
        for(int i = 0 ;i < dimension; ++i) {
           for(int j = 0 ; j < dimension; ++j) {
               if(i != j) {
                   C[i][j] = -matrix_1[i][j] /matrix_1[i][i];
               }
           }
           D[i] = matrix_1[i][dimension] /matrix_1[i][i];
           C[i][i] = 0;
       }
        System.out.println("C matrix: ");
        for(int i = 0 ;i < dimension; ++i) {
            for(int j = 0 ; j < dimension; ++j) {
                System.out.print(C[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println("D vector: ");
        for(int i = 0 ; i < dimension; ++i) {
            System.out.print(D[i] + " ");
        }
        System.out.println();
        double[] newX = new double[dimension];
        double[] oldX = new double[dimension];
        for(int i = 0 ;i < dimension; ++i) {
            newX[i] = D[i];
            oldX[i] = D[i];
        }

       for(int i = 0 ;i < dimension; ++i) {
           newX[i] = 0;
           for(int j = 0 ;j < i; ++j) {
               newX[i] += C[i][j]* newX[j];
           }

           for(int j = i + 1; j < dimension; ++j) {
               newX[i] += C[i][j] * newX[j];
           }

           newX[i] += D[i];
       }
       while (!checkIfLessThanEpsilon(dimension, newX, oldX, epsilon)){
            for(int i = 0 ;i < dimension; ++i) {
                oldX[i] = newX[i];
            }
            for(int i = 0 ;i < dimension; ++i) {
                newX[i] = 0;
                for(int j = 0 ;j < i; ++j) {
                    newX[i] += C[i][j]* newX[j];
                }

                for(int j = i + 1; j < dimension; ++j) {
                    newX[i] += C[i][j] * newX[j];
                }

               newX[i] += D[i];
                newX[i] = ((double)((int)(newX[i] * 1000))) / 1000;
            }
        };

        double[][] result = new double[2][dimension];
        for(int i = 0 ; i < dimension; ++i) {
            result[0][i] = newX[i];
            result[1][i] = Math.abs(newX[i] - D[i]);
        }

        return result;
   }

   public static void rearrangeMatrix(Matrix matrix) {
       Matrix matrix_0 = matrix.cloneMatrix();
       double[][] matrix_0_0 = matrix_0.getMatrix();
       int dimension = matrix.getDimension();
       for(int i = 0 ;i < dimension - 1; ++i) {
           for(int j = i + 1; j < dimension; ++j) {
               if(matrix_0_0[i][i] < matrix_0_0[j][i]) {
                   matrix.swapTwoRow(i, j);
                   matrix_0.swapTwoRow(i, j);
               }
           }

           for(int j = i+1; j < dimension; ++j) {
               for(int k = 0 ;k <= dimension; ++k) {
                   matrix_0_0[j][k] -= matrix_0_0[i][k] * matrix_0_0[i][i] / matrix_0_0[i][j];
               }
           }
       }
   }

   public static boolean checkIfLessThanEpsilon(int length,
                                                double[] newX,
                                                double[] oldX,
                                                double epsilon) {
       for(int i = 0 ;i < length; ++i) {
           if(Math.abs(newX[i] - oldX[i]) > epsilon) return false;
       }

       return true;
   }

}

package Algorithm;

import Equation.LinearEquation;
import Model.Matrix;

public class Seidel {
   public static double[][] seidelMethod(Matrix matrix, double epsilon) {
       int dimension = matrix.getDimension();
       Matrix copyMatrix = matrix.cloneMatrix();
       copyMatrix.toTriangular(0);
       copyMatrix.printMatrix();
       if(copyMatrix.getDeterminate() == 0) {
           return null;
       } else {
           System.out.println("Determinate of matrix: " + copyMatrix.getDeterminate());
       }
       double[][] C = new double[dimension][dimension];
       double[] D = new double[dimension];
       rearrangeMatrix(matrix);
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
            oldX[i] = 0;
            newX[i] = D[i];
        }
        double[] temp = new double[dimension];
        while(!checkIfLessThanEpsilon(dimension, newX, oldX, epsilon)) {
            for(int i =  0; i < dimension; ++i) {
                temp[i] = newX[i];
                newX[i] = 0;
            }

            for(int i = 0 ;i < dimension; ++i) {
                for(int j = 0 ;j < i; ++j) {
                    newX[i] += C[i][j]* newX[j];
                }

                for(int j = i + 1; j < dimension; ++j) {
                    newX[i] += C[i][j] * oldX[j];
                }

               newX[i] += D[i];
            }
            for(int i = 0 ;i < dimension; ++i) {
                oldX[i] = temp[i];
            }
//            System.out.println("New X: ");
//            for(int i = 0 ;i < dimension; ++i) {
//                System.out.print(newX[i] + " ");
//            }
//            System.out.println();
//            oldX = newX;
//            newX = LinearEquation.addVector(dimension, LinearEquation.multiMatrix(dimension, C, oldX),D);
        }

        double[][] result = new double[2][dimension];
        for(int i = 0 ; i < dimension; ++i) {
            result[0][i] = newX[i];
            result[1][i] = D[i];
        }

        return result;
   }

   public static void rearrangeMatrix(Matrix matrix) {
       int dimension = matrix.getDimension();
       double[][] matrix_1 = matrix.getMatrix();
       for(int i = 0 ;i < dimension - 1; ++i) {
           for(int j = i + 1; j < dimension; ++j) {
               if(matrix_1[i][i] == 0 && matrix_1[j][i] != 0) {
                   matrix.swapTwoRow(i, j);
                   break;
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

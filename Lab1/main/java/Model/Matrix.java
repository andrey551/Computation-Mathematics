package Model;

import Equation.LinearEquation;

public class Matrix {
    private int dimension;
    private double matrix[][];

    public Matrix(int dimension, double[][] matrix) {
        this.dimension = dimension;
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getDimension() {
        return dimension;
    }

    public int isTriangular() {
        int dimension = this.getDimension();
        double[][] matrix_1 = this.getMatrix();
        boolean isUpperTriangular = true;
        boolean isLowerTriangular = true;

        for(int i = 0; i < dimension; ++i) {
            for(int j = i + 1;j < dimension; ++j) {
                if(matrix_1[i][j] != 0) {
                    isUpperTriangular = false;
                    break;
                }

            }
        }

        for(int i = 0; i < dimension; ++i) {
            for(int j = 0 ;j < i; ++j) {
                if(matrix_1[i][j] != 0) {
                    isLowerTriangular = false;
                    break;
                }

            }
        }

        if(isUpperTriangular) return 1;
        if(isLowerTriangular) return -1;
        return 0;
    }

    public boolean toTriangular( int row) {
        double[][] matrix_1 = this.getMatrix();
        if(row == this.getDimension() - 1 && matrix_1[row][row] != 0) return true;
        if(matrix_1[row][row] == 0) {
            int i;
            for(i = row + 1; i < this.getDimension(); ++i) {
                if(matrix_1[i][row] != 0) {
                    swapTwoRow( i, row);
                    break;
                }
            }
            if( i == this.getDimension()) return false;
        }

        for(int i = row + 1; i < this.getDimension(); ++i) {
            if(matrix_1[i][row] != 0) {
                    subtractSecondRow( row, i , matrix_1[i][row]/matrix_1[row][row]);
            }
        }

        return toTriangular( row+1);
    }

    public double getDeterminate() {
        double res = 1;
        double[][] matrix_1 = this.getMatrix();
        if(isTriangular() == 0) {
            if(!toTriangular( 0)) return 0;
        }


        for(int i = 0 ;i < this.getDimension(); ++i) {
            res *= matrix_1[i][i];
        }

        return res;
    }
    public void swapTwoRow( int firstRow, int secondRow) {
        double[][] matrix_1 = this.getMatrix();
        double temp;
        for(int i = 0; i < this.getDimension(); ++i) {
            temp = matrix_1[firstRow][i];
            matrix_1[firstRow][i] = matrix_1[secondRow][i] ;
            matrix_1[secondRow][i] = temp;
        }
    }

    public void subtractSecondRow( int firstRow, int secondRow , double times) {
        double[][] matrix_1 = this.getMatrix();
        for(int i = 0 ; i <= this.getDimension(); ++i) {
            matrix_1[secondRow][i] -= matrix_1[firstRow][i]*times;
        }
    }

    public void printMatrix() {
        System.out.println("Print matrix: ");
        for(int i = 0 ;i < this.getDimension(); ++i) {
            for(int j = 0; j <= this.getDimension(); ++j) {
                System.out.print(this.matrix[i][j] + " ");
            }

            System.out.println();
        }
    }

    public void printDeterminate() {
        System.out.println("Determinate of matrix: ");
        System.out.println(getDeterminate());
    }

    public Matrix cloneMatrix() {
        int n = this.dimension;
        double[][] matrix = new double[n][ n + 1];
        for(int i = 0 ;i < n; ++i) {
            for(int j = 0; j < n +1 ; ++j) {
                matrix[i][j] = this.matrix[i][j];
            }
        }

        return new Matrix(n, matrix);
    }
}

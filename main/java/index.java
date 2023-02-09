import Algorithm.Seidel;
import Model.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class index {
    private static final String URL = "src/main/input.txt";
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        Matrix matrix = null;
        String isFile;
        System.out.println(" Do you want to get data from console(1) or file(2) ?");
        isFile = scanner.nextLine();
        if(isFile.length() != 1 || (isFile.charAt(0) != '1' && isFile.charAt(0) != '2')) {
            System.out.println("Input is unexpected, program is finish automatically!");
        } else if ( isFile.charAt(0) == '1') {
            matrix = getMatrixByConsole();
        } else {
            matrix = getMatrixByFile();
        }

        double[][] result = Seidel.seidelMethod(matrix, 0.1);
        if(result == null) {
            System.out.println("This system has no option or infinity options! ");
        } else {
            System.out.print("Solution set of equations: ");
            for(int i = 0 ;i < matrix.getDimension(); ++i) {
                System.out.print(result[0][i] + " ");
            }
            System.out.println();
            System.out.print("Residual vector :");
            for(int i = 0 ;i < matrix.getDimension(); ++i) {
                System.out.print(result[1][i] + " ");
            }
            System.out.println();
            System.out.println("System finished!");
        }
    }

    public static Matrix getMatrixByConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please type dimension of matrix: ");
        int dimension = scanner.nextInt();
        double[][] matrix = new double[dimension][dimension + 1];
        System.out.println("Please type matrix: ");
        for(int i = 0 ;i < dimension; ++i) {
            for(int j = 0 ; j <= dimension; ++j) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        return new Matrix(dimension, matrix);
    }

    public static Matrix getMatrixByFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(URL));
        int dimension = Integer.parseInt(scanner.nextLine());
        double[][] matrix = new double[dimension][dimension + 1];
        for(int i = 0 ; i < dimension; ++i) {
            String line = scanner.nextLine();
            String[] setOfValue = line.split(" ");
            for(int j = 0; j <= dimension; ++j) {
                    matrix[i][j] = Double.parseDouble(setOfValue[j]);
            }
        }

        return new Matrix(dimension, matrix);
    }
}

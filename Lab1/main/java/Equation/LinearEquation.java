package Equation;

public class LinearEquation {
    public static double[] multiMatrix(int length, double[][] C, double[] D) {
        double[] result = new double[length];
        for(int i = 0; i < length; ++i) {
            result[i] = 0;
            for(int j = 0; j < length; ++j) {
                result[i] += C[i][j] * D[j];
            }
        }

        return result;
    }

    public static double[] addVector(int length, double[] X, double[] D) {
        double[] result = new double[length];

        for(int i = 0 ;i < length; ++i) {
            result[i] = X[i] + D[i];
        }

        return result;
    }
}

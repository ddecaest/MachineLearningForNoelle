package com.asheron.util;

public class ConverterUtil {

    // Assumes matrix is equal width and height
    static double[] imageIntensityToNNInput(int[][] matrix) {
        final double[] toReturn = new double[matrix.length * matrix[0].length];
        for(int i=0; i<matrix.length; i++) {
            System.arraycopy(matrix[i], 0, toReturn, matrix[0].length * i, matrix[i].length);
        }
        for(int i=0; i<toReturn.length; i++) {
            toReturn[i] = toReturn[i]/255.0;
        }
        return toReturn;
    }
}

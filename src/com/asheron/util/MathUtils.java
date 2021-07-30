package com.asheron.util;

public final class MathUtils {

    public static double dotProduct(double[] x, double[] y) {
        double result = 0;
        for (int i = 0; i < x.length; i++) {
            result += x[i] * y[i];
        }
        return result;
    }

    public static double sigmoid(double number) {
        return (1.0/( 1.0 + Math.pow(Math.E,(-1*number))));
    }

    public static double sigmoidDerivative(double number) {
        final double sigmoidOfNumber = sigmoid(number);
        return sigmoidOfNumber*(1-sigmoidOfNumber);
    }

    public static double reLU(double number) {
        return Math.max(0, number);
    }
}

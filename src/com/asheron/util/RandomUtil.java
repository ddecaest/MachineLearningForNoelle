package com.asheron.util;

import java.util.Random;

public class RandomUtil {

    public static final Random random = new Random();

    public static double[] generateVectorValuesBetweenZeroAndOne(int size) {
        double[] toReturn = new double[size];
        for(int i=0; i<size; i++) {
            toReturn[i] = random.nextDouble();
        }
        return toReturn;
    }
}

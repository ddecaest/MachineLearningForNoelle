package com.asheron;

import com.asheron.data.mnist.DigitImageWithLabel;
import com.asheron.data.mnist.MnistReader;
import com.asheron.frontend.JPanelBackedByMatrix;
import com.asheron.data.mnist.DigitImageSimpleNN;
import com.asheron.util.RandomUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        final DigitImageSimpleNN simpleNN = new DigitImageSimpleNN(784, 15, 15, 10);

        final List<DigitImageWithLabel> dataToTrainWith = MnistReader.read();

        int[] frequency = new int[10];
        for (DigitImageWithLabel digitImageWithLabel : dataToTrainWith) {
            simpleNN.offerInput(toDoubleList(digitImageWithLabel));
            frequency[simpleNN.getNeuronIndexOfHighestOutput()]++;

        }

        createFrame(dataToTrainWith.get(0).image);
        offerImage();
    }

    private static double[] toDoubleList(DigitImageWithLabel image) {
        double[] input = new double[784];
        for(int i=0; i<28; i++) {
            for(int j=0; j<28; j++) {
                input[i*28 + j] = image.image[i][j]/255d;
            }
        }
        return input;
    }

    private static void randomInputdoSimpleNN() {
        DigitImageSimpleNN digitImageSimpleNN = new DigitImageSimpleNN(784, 15, 15, 10);
        digitImageSimpleNN.offerInput(RandomUtil.generateVectorValuesBetweenZeroAndOne(784));
        System.out.println(Arrays.toString(digitImageSimpleNN.checkOutputLayer()));
    }

    private static void offerImage() {
        DigitImageSimpleNN digitImageSimpleNN = new DigitImageSimpleNN(784, 15, 15, 10);
        digitImageSimpleNN.offerInput(RandomUtil.generateVectorValuesBetweenZeroAndOne(784));
        System.out.println(Arrays.toString(digitImageSimpleNN.checkOutputLayer()));
    }

    private static void createFrame(int[][] image) {

        EventQueue.invokeLater(() -> {
            final JFrame frame = new JFrame();
            frame.setMinimumSize(new Dimension(270, 270));
            frame.setContentPane(new JPanelBackedByMatrix(image));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

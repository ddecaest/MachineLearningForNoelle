package com.asheron.data.mnist;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MnistReader {

    public static List<DigitImageWithLabel> read() throws IOException {

        final DataInputStream labels = new DataInputStream(MnistReader.class.getClassLoader().getResourceAsStream("com/asheron/data/mnist/train-labels.idx1-ubyte"));
        final DataInputStream images = new DataInputStream(MnistReader.class.getClassLoader().getResourceAsStream("com/asheron/data/mnist/train-images.idx3-ubyte"));

        readInMagicNumbers(labels, images);

        final int numLabels = labels.readInt();
        final int numImages = images.readInt();
        if (numLabels != numImages) {
            throw new RuntimeException("Image file and label file do not contain the same number of entries.");
        }

        final List<DigitImageWithLabel> toReturn = new ArrayList<>(numImages);

        // Read in labels are one big byte array
        final byte[] labelsData = new byte[numLabels];
        labels.read(labelsData);

        // Read in images as one big byte array
        final int imageWidth = images.readInt();
        final int imageHeight = images.readInt();
        final byte[] imagesData = new byte[numLabels * imageHeight * imageWidth];
        images.read(imagesData);

        int imageIndex = 0;
        for (int i = 0; i < numLabels; i++) {
            final int[][] image = new int[imageWidth][imageHeight];
            for(int y=0; y<imageHeight; y++) {
                for(int x=0; x<imageWidth; x++) {
                    image[x][y] = ((imagesData[imageIndex++] & 0xff));
                }
            }

            final int digitLabel = labelsData[i];

            toReturn.add(new DigitImageWithLabel(digitLabel, image));
        }

        images.close();
        labels.close();

        return toReturn;
    }

    private static void readInMagicNumbers(DataInputStream labels, DataInputStream images) throws IOException {

        final int magicNumberLabels = labels.readInt();
        final int magicNumberImages = images.readInt();
        if (magicNumberLabels != 2049) {
            throw new RuntimeException("Label file has wrong magic number: " + magicNumberLabels + " (should be 2049)");
        }
        if (magicNumberImages != 2051) {
            throw new RuntimeException("Image file has wrong magic number: " + magicNumberImages + " (should be 2051)");
        }
    }
}
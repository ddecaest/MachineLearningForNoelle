package com.asheron.data.mnist;

public class DigitImageWithLabel {

    public int digitLabel;
    public int[][] image;

    public DigitImageWithLabel(int digitLabel, int[][] image) {
        this.digitLabel = digitLabel;
        this.image = image;
    }
}

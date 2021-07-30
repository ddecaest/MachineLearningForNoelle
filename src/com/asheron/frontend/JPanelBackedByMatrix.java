package com.asheron.frontend;

import javax.swing.*;
import java.awt.*;

public class JPanelBackedByMatrix extends JPanel {

    private final int[][] matrix;

    public JPanelBackedByMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public void paintComponent(Graphics g) {

        final int x_per = (int) (g.getClipBounds().getWidth() / matrix.length);
        final int y_per = (int) (g.getClipBounds().getHeight() / matrix[0].length);

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                g.setColor(Colors.colors[matrix[i][j]]);
                g.fillRect(i*x_per, j*y_per, x_per, y_per);
            }
        }
    }
}

package com.asheron.frontend;

import java.awt.*;

public abstract class Colors {

    public static Color[] colors;

    static {
        colors = new Color[256];
        for(int i=0; i<256; i++) {
            colors[i] = new Color(i,i,i);
        }
    }
}

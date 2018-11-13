package com.maheer.colorpicker;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {
        JFrame colorPicker = new ColorPicker();
        colorPicker.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

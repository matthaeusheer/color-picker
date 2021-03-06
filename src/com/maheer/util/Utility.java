package com.maheer.util;

import com.maheer.colorpicker.ColorModel;

import java.awt.Color;

public class Utility {

    public static Color addValueToAllChannels(Color color, int value) {

        int red = truncateToBounds(color.getRed() + value);
        int green = truncateToBounds(color.getGreen() + value);
        int blue = truncateToBounds(color.getBlue() + value);

        return new Color(red, green, blue);
    }

    public static int truncateToBounds(int colorValue) {

        if (colorValue > ColorModel.MAX_COL_VAL) {
            return ColorModel.MAX_COL_VAL;
        } else if (colorValue < ColorModel.MIN_COL_VAL) {
            return ColorModel.MIN_COL_VAL;
        }
        return colorValue;
    }

    public static Color setColorComponent(Color color, ColorModel.RgbType type, int colorVal) {

        if (type == ColorModel.RgbType.R) {
            return new Color(colorVal, color.getGreen(), color.getBlue());
        } else if (type == ColorModel.RgbType.G) {
            return new Color(color.getRed(), colorVal, color.getBlue());
        } else if (type == ColorModel.RgbType.B) {
            return new Color(color.getRed(), color.getGreen(), colorVal);
        } else {
            throw new IllegalArgumentException("Component type not valid rgbType.");
        }
    }
}

package com.maheer.components;

import com.maheer.colorpicker.ColorModel;

public interface ColorListener {
    void colorValueChanged(int newColorVal, ColorModel.rgbType type);
}

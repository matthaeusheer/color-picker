package com.maheer.components;

import com.maheer.colorpicker.ColorModel;

import javax.swing.*;
import java.awt.event.ActionListener;

abstract class AbstractTextField extends JTextField implements ColorListener, ActionListener {

    private final ColorModel.rgbType type;
    private int colorValue;
    private final ColorModel model;

    public AbstractTextField(ColorModel model, ColorModel.rgbType type,int colorValue) {
        this.type = type;
        this.model = model;
        this.colorValue = colorValue;
        model.addColorListener(this);
    }

    public ColorModel.rgbType getType() {
        return type;
    }

    public ColorModel getModel() {
        return model;
    }

    public int getColorValue() {
        return colorValue;
    }

    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
    }
}

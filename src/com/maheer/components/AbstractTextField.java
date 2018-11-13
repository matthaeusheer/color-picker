package com.maheer.components;

import com.maheer.colorpicker.ColorModel;

import java.awt.event.ActionListener;
import javax.swing.JTextField;


@SuppressWarnings("serial")
abstract class AbstractTextField extends JTextField implements ChannelListener, ActionListener {

    private final ColorModel.RgbType type;
    private int colorValue;
    private final ColorModel model;

    public AbstractTextField(ColorModel model, ColorModel.RgbType type, int colorValue) {
        this.type = type;
        this.model = model;
        this.colorValue = colorValue;
        model.addChannelListener(this, type);
    }

    public ColorModel.RgbType getType() {
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

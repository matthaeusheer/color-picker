package com.maheer.components;

import com.maheer.colorpicker.ColorModel;

import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class ScrollBar extends JScrollBar implements AdjustmentListener, ColorListener {

    private String name;
    private ColorModel model;
    private ColorModel.rgbType rgbType;

    public ScrollBar(ColorModel model, ColorModel.rgbType rgbType, String name, int orientation, int val) {
        super(orientation, val, 0, 0, 255);
        this.model = model;
        this.rgbType = rgbType;
        this.name = name;
        addAdjustmentListener(this);
        model.addColorListener(this);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        int newColorValue = e.getValue();
        model.setColorComponent(newColorValue, rgbType);
    }

    @Override
    public void colorValueChanged(int newColorVal, ColorModel.rgbType rgbType) {
        if (rgbType == this.rgbType)
            setValue(newColorVal);
    }
}

package com.maheer.components;

import com.maheer.colorpicker.ColorModel;

import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ColorPanel extends JPanel implements ColorListener {

    private Color color;

    public ColorPanel(ColorModel model) {
        this.color = model.getColor();
        setBackground(color);
        model.addColorListener(this);
    }
    @Override
    public void colorValueChanged(Color newColor) {
    	setBackground(newColor);
    }
}

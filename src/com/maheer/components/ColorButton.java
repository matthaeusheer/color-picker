package com.maheer.components;

import com.maheer.colorpicker.ColorModel;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ColorButton extends JRadioButton implements ActionListener, ColorListener{

    private Color color;
    private ColorModel model;

    public ColorButton(ColorModel model, String label, Color color) {
        super(label);
        this.color = color;
        this.model = model;
        model.addColorListener(this);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setColor(color);
        setSelected(true);	
    }

	@Override
	public void colorValueChanged(Color newColor) {
		if (newColor.equals(color)) {
			setSelected(true);
		} else {
			setSelected(false);
		}
		
	}
}

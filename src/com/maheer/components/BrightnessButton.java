package com.maheer.components;

import com.maheer.colorpicker.ColorModel;
import com.maheer.util.Utility;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrightnessButton extends JButton implements ActionListener {

    private final int BRIGHTNESS_STEP = 10;
    private String label;
    private Color buttonColor;
    private ColorModel model;
    private brightnessDirection direction;

    public enum brightnessDirection {
        BRIGHTER, DARKER;
    }

    public BrightnessButton(String label, Color buttonColor, ColorModel model, brightnessDirection direction) {
        super(label);
        this.label = label;
        this.buttonColor = buttonColor;
        this.model = model;
        this.direction = direction;
        addActionListener(this);
        setBackground(buttonColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Made the image: " + label.toLowerCase());

        if (direction == brightnessDirection.BRIGHTER) {
            model.setColor(Utility.addValueToAllChannels(model.getColor(), BRIGHTNESS_STEP));
        } else if (direction == brightnessDirection.DARKER) {
            model.setColor(Utility.addValueToAllChannels(model.getColor(), - BRIGHTNESS_STEP));
        } else {
            throw new IllegalArgumentException("Only brighter / darker directions allowed.");
        }
    }
}

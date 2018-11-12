package com.maheer.components;

import com.maheer.colorpicker.ColorModel;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorButton extends JRadioButton implements ActionListener {

    private String label;
    private Color color;
    private ColorModel model;

    public ColorButton(ColorModel model, String label, Color color) {
        super(label);
        this.label = label;
        this.color = color;
        this.model = model;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Switched to color: " + label);
        model.setColor(color);
        setSelected(false);	// XXX das finde ich schade, eigentlich sollten die Radio-Buttons aktiv sein wenn die entsprechende Farbe eingestellt wird.
        					//     Dazu müsste man hier auch einen Listener im Model registrieren, der auf die Farbe (nicht auf einen Farbkanal) hört.

    }
}

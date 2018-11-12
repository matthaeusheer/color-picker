package com.maheer.components;

import com.maheer.colorpicker.ColorModel;
import com.maheer.util.Utility;

import javax.swing.JPanel;
import java.awt.*;

public class ColorPanel extends JPanel implements ColorListener {

    private Color color;

    public ColorPanel(ColorModel model) {
        this.color = model.getColor();
        setBackground(color);
        model.addColorListener(this);
    }
    @Override
    public void colorValueChanged(int newColorVal, ColorModel.rgbType type) {
        /*
        System.out.println("Color colorValue changed to: " +
                "(" + color.getRed() + " " + color.getGreen() + " " + color.getBlue() + ")");
        */
        Color newColor = Utility.setColorComponent(color, type, newColorVal);
        color = newColor;
        // XXX Problem mit diesem Ansatz: Es werden jetzt (kurzfristig) auch Farben dargestellt die nie eingesetellt worden sind, weil sie nur die einzelnen Farbkanäle publizieren.
        
        // XXX OK, aber warum schreiben Sie nicht gerade
        // color = Utility.setColorComponent(color, type, newColorVal);

        setBackground(newColor);
    }
}

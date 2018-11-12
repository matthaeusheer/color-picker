package com.maheer.components;

import com.maheer.colorpicker.ColorModel;
import com.maheer.util.Utility;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

public class RgbTextField extends AbstractTextField {

    private List<TextFieldListener> listeners = new LinkedList<>();

    public RgbTextField(ColorModel model, ColorModel.rgbType type, int colorValue) {
        super(model, type, colorValue);
        setText(String.valueOf(Utility.truncateToBounds(colorValue)));
        addActionListener(this);
    }

    public void addListener(TextFieldListener listener) {
        listeners.add(listener);
    }

    @Override
    public void colorValueChanged(int newColorVal, ColorModel.rgbType type) {
        if (type == getType()) {
            setColorValue(newColorVal);
            setText(String.valueOf(newColorVal));

            for (TextFieldListener listener : listeners) {	// XXX habe das Gefühl dass das nicht nötig ist, das wird bereits durch das Textfeld gemacht.
                listener.textFieldChanged(newColorVal);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            int parsedColorValue = Integer.parseInt(getText());
            int boundColorValue = Utility.truncateToBounds(parsedColorValue);
            setText(String.valueOf(boundColorValue));
            getModel().setColorComponent(boundColorValue, getType());
            setColorValue(boundColorValue);

        } catch (NumberFormatException exception) {
            System.out.println("Only integers allowed!");
        }
    }


}

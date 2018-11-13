package com.maheer.components;

import com.maheer.colorpicker.ColorModel;
import com.maheer.util.Utility;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class RgbTextField extends AbstractTextField {

    private List<TextFieldListener> listeners = new LinkedList<>();

    public RgbTextField(ColorModel model, ColorModel.RgbType type, int colorValue) {
        super(model, type, colorValue);
        setText(String.valueOf(Utility.truncateToBounds(colorValue)));
        addActionListener(this);
    }

    public void addListener(TextFieldListener listener) {
        listeners.add(listener);
    }

    @Override
    public void colorValueChanged(int newColorVal) {

        setColorValue(newColorVal);
        setText(String.valueOf(newColorVal));

        for (TextFieldListener listener : listeners) {
            listener.textFieldChanged(String.valueOf(newColorVal));
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

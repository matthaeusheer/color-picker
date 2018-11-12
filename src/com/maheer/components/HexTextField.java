package com.maheer.components;

import javax.swing.*;

public class HexTextField extends JTextField implements TextFieldListener {

    public HexTextField(RgbTextField rgbTextFieldToListenTo) {
        rgbTextFieldToListenTo.addListener(this);	// XXX ok, variante wäre dass sich dieser Control auch als Color-Listener registriert.
        setText(Integer.toHexString(rgbTextFieldToListenTo.getColorValue()));
        setEditable(false);
    }

    @Override
    public void textFieldChanged(int newValue) {

        String hexStr = Integer.toHexString(newValue);
        setText(hexStr);
    }
}

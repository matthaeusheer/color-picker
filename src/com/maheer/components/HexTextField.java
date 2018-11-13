package com.maheer.components;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class HexTextField extends JTextField implements TextFieldListener {

    public HexTextField(RgbTextField rgbTextFieldToListenTo) {
        rgbTextFieldToListenTo.addListener(this);
        setText(Integer.toHexString(rgbTextFieldToListenTo.getColorValue()));
        setEditable(false);
    }

    @Override
    public void textFieldChanged(String newValue) {
        String hexStr = Integer.toHexString(Integer.parseInt(newValue));
        setText(hexStr);
    }
}

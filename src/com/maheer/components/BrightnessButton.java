package com.maheer.components;

import com.maheer.colorpicker.ColorModel;
import com.maheer.util.Utility;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class BrightnessButton extends JButton implements ActionListener, ColorListener {

    private final int BRIGHTNESS_STEP = 10;
    private ColorModel model;
    private brightnessDirection direction;
    private boolean isEnabled;

    public enum brightnessDirection {
        BRIGHTER, DARKER;
    }

    public BrightnessButton(String label, Color buttonColor, ColorModel model, brightnessDirection direction) {
        super(label);
        this.model = model;
        this.direction = direction;
        model.addColorListener(this);
        addActionListener(this);
        setBackground(buttonColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (direction == brightnessDirection.BRIGHTER) {
            model.setColor(Utility.addValueToAllChannels(model.getColor(), BRIGHTNESS_STEP));
        } else if (direction == brightnessDirection.DARKER) {
            model.setColor(Utility.addValueToAllChannels(model.getColor(), - BRIGHTNESS_STEP));
        } else {
            throw new IllegalArgumentException("Only brighter / darker directions allowed.");
        }
    }

	@Override
	public void colorValueChanged(Color newColor) {
		isEnabled = true;
		if (direction.equals(brightnessDirection.BRIGHTER) && newColor.equals(Color.WHITE)) {
			isEnabled = false;
		}
		
		if (direction.equals(brightnessDirection.DARKER) && newColor.equals(Color.BLACK)) {
			isEnabled = false;
		}

		setEnabled(isEnabled);
		
	}
}

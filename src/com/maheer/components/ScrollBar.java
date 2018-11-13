package com.maheer.components;

import com.maheer.colorpicker.ColorModel;
import com.maheer.colorpicker.ColorModel.RgbType;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollBar;

@SuppressWarnings("serial")
public class ScrollBar extends JScrollBar implements AdjustmentListener, ChannelListener {

    private ColorModel model;
    private ColorModel.RgbType rgbType;

    public ScrollBar(ColorModel model, ColorModel.RgbType rgbType, int orientation, int val) {
        super(orientation, val, 0, 0, 255);
        this.model = model;
        this.rgbType = rgbType;
        addAdjustmentListener(this);
        model.addChannelListener(this, rgbType);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        int newColorValue = e.getValue();
        model.setColorComponent(newColorValue, rgbType);
    }

    @Override
    public void colorValueChanged(int newColorVal) {
    	setValue(newColorVal);
    }

	@Override
	public RgbType getType() {
		return rgbType;
	}
}

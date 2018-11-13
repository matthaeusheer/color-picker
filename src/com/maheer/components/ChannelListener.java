package com.maheer.components;

import com.maheer.colorpicker.ColorModel;

public interface ChannelListener {
	void colorValueChanged(int newColorVal);
	ColorModel.RgbType getType();
}

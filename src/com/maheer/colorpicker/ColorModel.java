package com.maheer.colorpicker;

import com.maheer.components.ChannelListener;
import com.maheer.components.ColorListener;

import java.awt.Color;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ColorModel {

    private Color color;
    private final List<ColorListener> colorListeners = new LinkedList<>();
    private final EnumMap<RgbType, List<ChannelListener>> channelListeners = new EnumMap<>(RgbType.class);

    public static final int MAX_COL_VAL = 255;
    public static final int MIN_COL_VAL = 0;
    public enum RgbType {
        R, G, B;
    }

    public ColorModel(Color initColor) {
        this.color = initColor;
        
        // Initialize the channelListener lists inside the EnumMap.
        for (RgbType type : RgbType.values()) {
        	channelListeners.put(type, new LinkedList<>());
        }
    }
    
    public void addChannelListener(ChannelListener listener, RgbType type) {
    	channelListeners.get(type).add(listener);
    	}
    
    public void removeChannelListener(ChannelListener listener, RgbType type) {
    	channelListeners.get(type).remove(listener);
    }

    public void addColorListener(ColorListener listener) {
        colorListeners.add(listener);
    }

    public void removeColorListener(ColorListener listener) {
        colorListeners.remove(listener);
    }

    public void setColor(Color newColor) {
        if (!newColor.equals(this.color)) {
        
            notifyListeners(newColor);
            this.color = newColor;
        }
    }
    
    private void notifyListeners(Color newColor) {
    	
    	// Notify listeners who registered as complete color listeners (receive the full color upon change).
    	for (ColorListener colorListener : colorListeners) {
        	colorListener.colorValueChanged(newColor);
        }
        
    	// Notify listeners who registered only to a single color channel.
        Map<RgbType, Integer> changedValues = changedRgbTypes(color, newColor);
        
        for (Map.Entry<RgbType, Integer> typeColorPair : changedValues.entrySet()) {
        
            for (ChannelListener channelListener : channelListeners.get(typeColorPair.getKey())) {
                channelListener.colorValueChanged(typeColorPair.getValue());
            }
        }
    }

    public Color getColor() {
    	return color; // This is o.k. since Color is immutable.
    }

    public EnumMap<RgbType, Integer> changedRgbTypes(Color oldColor, Color newColor) {

        EnumMap<RgbType, Integer> changedTypes = new EnumMap<>(RgbType.class);

        for (RgbType type : RgbType.values()) {
            if (getRgbTypeValue(oldColor, type) != getRgbTypeValue(newColor, type)) {
                changedTypes.put(type, getRgbTypeValue(newColor, type));
            }
        }
        return changedTypes;
    }

    public void setR(int rVal) {
        Color newColor = new Color(rVal, color.getGreen(), color.getBlue());
        setColor(newColor);
    }

    public void setG(int gVal) {
        Color newColor = new Color(color.getRed(), gVal, color.getBlue());
        setColor(newColor);
    }

    public void setB(int bVal) {
        Color newColor = new Color(color.getRed(), color.getGreen(), bVal);
        setColor(newColor);
    }

    public void setColorComponent(int colorValue, RgbType componentType) {
        if (componentType == RgbType.R) {
            setR(colorValue);
        } else if (componentType == RgbType.G) {
            setG(colorValue);
        } else if (componentType == RgbType.B) {
            setB(colorValue);
        } else {
            throw new IllegalArgumentException("Component type not valid rgbType.");
        }
    }

    private int getRgbTypeValue(Color color, RgbType type) {
        if (type == RgbType.R) {
            return color.getRed();
        } else if (type == RgbType.G) {
            return color.getGreen();
        } else if (type == RgbType.B) {
            return color.getBlue();
        } else {
            throw new IllegalArgumentException("Component type not valid rgbType.");
        }
    }
}

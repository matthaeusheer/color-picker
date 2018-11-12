package com.maheer.colorpicker;

import com.maheer.components.ColorListener;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ColorModel {

    private Color color;
    private List<ColorListener> listeners = new LinkedList<>();	// XXX diese Liste könnte final deklariert werden.

    public static final int MAX_COL_VAL = 255;
    public static final int MIN_COL_VAL = 0;
    public enum rgbType {	// XXX Typnamen starten typischerweise mit Grossbuchstaben
        R, G, B;
    }

    public ColorModel(Color initColor) {
        this.color = initColor;
    }

    public void addColorListener(ColorListener listener) {
        listeners.add(listener);
    }

    public void removeColorListener(ColorListener listener) {
        listeners.remove(listener);
    }

    public void setColor(Color newColor) {
        if (!newColor.equals(this.color)) {
            Map<rgbType, Integer> changedValues = changedRgbTypes(color, newColor);
            this.color = newColor;
            for (ColorListener listener : listeners) {
                for (Map.Entry<rgbType, Integer> typeColorPair : changedValues.entrySet()) {
                	// XXX OK. Schön wäre noch wenn sich ein Listener nur für einen Farbkanal registrieren könnte.
                    listener.colorValueChanged(typeColorPair.getValue(), typeColorPair.getKey());

                }
            }
        }
    }

    public Color getColor() {
    	// XXX Color ist immutable, daher könnten sie ohne Probleme auch "return color" schreiben.
        return new Color(color.getRed(), color.getGreen(), color.getBlue());
    }

    public Map<rgbType, Integer> changedRgbTypes(Color oldColor, Color newColor) {
    	// XXX hier würde sich ein EnumMap anbieten
        Map<rgbType, Integer> changedTypes = new HashMap<>();

        for (rgbType type : rgbType.values()) {
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

    public void setColorComponent(int colorValue, rgbType componentType) {
        if (componentType == rgbType.R) {
            setR(colorValue);
        } else if (componentType == rgbType.G) {
            setG(colorValue);
        } else if (componentType == rgbType.B) {
            setB(colorValue);
        } else {
            throw new IllegalArgumentException("Component type not valid rgbType.");
        }
    }

    private int getRgbTypeValue(Color color, rgbType type) {
        if (type == rgbType.R) {
            return color.getRed();
        } else if (type == rgbType.G) {
            return color.getGreen();
        } else if (type == rgbType.B) {
            return color.getBlue();
        } else {
            throw new IllegalArgumentException("Component type not valid rgbType.");
        }
    }
}

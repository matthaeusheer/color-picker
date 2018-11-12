package com.maheer.colorpicker;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        ColorPicker colorPicker = new ColorPicker();
        // XXX ich finde es etwas unschön dass hier eine INstanz erzeugt wird die gar nicht verwendet wird.
        //     Man könnte auch new ColorPicker() schreiben, aber das sieht dann für den Leser so aus als ob dieses Objekt ja gar nicht benötigt wird.
        //     Ich fände es besser wenn ColorPicker gerade ein JFrame wäre (z.B.) dann könnte dieses dann hier im Main angezeigt werden.
    }
}

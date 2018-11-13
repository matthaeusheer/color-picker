package com.maheer.colorpicker;

import com.maheer.components.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;



@SuppressWarnings("serial")
public class ColorPicker extends JFrame{

    public static final Color INIT_COLOR = new Color(212,175,55);
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;

    public ColorPicker() {
    	super("Mat's Color Picker");
        setupFrame();

        ColorModel colorModel = new ColorModel(INIT_COLOR);
        setupPanels(colorModel);
        
        pack();    
        setVisible(true);
    }

    private void setupFrame() {
        setResizable(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
    }

    /**
     * Sets up all the panels and components.
     * TODO: This should be nicely done in a PanelManager class or so. However it's not central to the assignment.
     */
    private void setupPanels(ColorModel colorModel) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));

        JPanel northPanel = new JPanel(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel(new GridLayout(1, 3));
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Add color window panel
        JPanel colorPanel = new ColorPanel(colorModel);
        colorPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT /2));
        southPanel.add(colorPanel);

        // Add RGB scroll bars
        JPanel sliderPanel = new JPanel(new GridLayout(3, 1));
        northPanel.add(sliderPanel, BorderLayout.WEST);
        ScrollBar scrollBarR = new ScrollBar(colorModel, ColorModel.RgbType.R, 0, INIT_COLOR.getRed());
        ScrollBar scrollBarG = new ScrollBar(colorModel, ColorModel.RgbType.G, 0, INIT_COLOR.getGreen());
        ScrollBar scrollBarB = new ScrollBar(colorModel, ColorModel.RgbType.B, 0, INIT_COLOR.getBlue());
        scrollBarR.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT / 2 /  3 / 2));
        scrollBarG.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT / 2 /  3 / 2));
        scrollBarB.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT / 2 /  3 / 2));
        sliderPanel.add(scrollBarR);
        sliderPanel.add(scrollBarG);
        sliderPanel.add(scrollBarB);

        // Add text fields and hex text field listeners
        JPanel rgbTextBoxesPanel = new JPanel(new GridLayout(3, 2));
        northPanel.add(rgbTextBoxesPanel, BorderLayout.CENTER);
        RgbTextField rTextField = new RgbTextField(colorModel, ColorModel.RgbType.R, INIT_COLOR.getRed());
        RgbTextField gTextField = new RgbTextField(colorModel, ColorModel.RgbType.G, INIT_COLOR.getGreen());
        RgbTextField bTextField = new RgbTextField(colorModel, ColorModel.RgbType.B, INIT_COLOR.getBlue());
        HexTextField rHexField = new HexTextField(rTextField);
        HexTextField gHexField = new HexTextField(gTextField);
        HexTextField bHexField = new HexTextField(bTextField);
        rgbTextBoxesPanel.add(rTextField);
        rgbTextBoxesPanel.add(rHexField);
        rgbTextBoxesPanel.add(gTextField);
        rgbTextBoxesPanel.add(gHexField);
        rgbTextBoxesPanel.add(bTextField);
        rgbTextBoxesPanel.add(bHexField);

        // Add color buttons
        JPanel colorButtonPanel = new JPanel(new GridLayout(5, 1));
        southPanel.add(colorButtonPanel, BorderLayout.CENTER);
        ColorButton redColorButton = new ColorButton(colorModel, "Red", Color.red);
        ColorButton orangeColorButton = new ColorButton(colorModel, "Orange", Color.orange);
        ColorButton cyanColorButton = new ColorButton(colorModel, "Cyan", Color.cyan);
        ColorButton magentaColorButton = new ColorButton(colorModel, "Magenta", Color.magenta);
        ColorButton pinkColorButton = new ColorButton(colorModel, "Pink", Color.pink);
        colorButtonPanel.add(redColorButton);
        colorButtonPanel.add(orangeColorButton);
        colorButtonPanel.add(cyanColorButton);
        colorButtonPanel.add(magentaColorButton);
        colorButtonPanel.add(pinkColorButton);

        // Add brightness buttons
        JPanel brightnessButtonPanel = new JPanel(new GridLayout(0, 1));
        southPanel.add(brightnessButtonPanel, BorderLayout.EAST);
        BrightnessButton brighterButton = new BrightnessButton("Brighter", Color.lightGray, colorModel, BrightnessButton.brightnessDirection.BRIGHTER);
        BrightnessButton darkerButton = new BrightnessButton("Darker", Color.darkGray, colorModel, BrightnessButton.brightnessDirection.DARKER);
        brightnessButtonPanel.add(brighterButton);
        brightnessButtonPanel.add(darkerButton);
        brighterButton.setPreferredSize(new Dimension(WIDTH / 4, (int) (HEIGHT * 0.3)));
    }
}

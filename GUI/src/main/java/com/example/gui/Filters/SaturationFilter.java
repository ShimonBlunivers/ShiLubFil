package com.example.gui.Filters;

import com.example.gui.HelloController;
import javafx.scene.effect.ColorAdjust;

public class SaturationFilter extends Filter {

    public SaturationFilter() {
        super("Saturation");
    }
    @Override
    public void apply() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(100);
        HelloController.imageView.setEffect(colorAdjust);
    }
}

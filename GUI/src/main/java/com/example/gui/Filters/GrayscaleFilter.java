package com.example.gui.Filters;

import com.example.gui.HelloController;
import javafx.animation.FadeTransition;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;

public class GrayscaleFilter extends Filter {
    public GrayscaleFilter() {
        super("Černobílý");
    }
    @Override
    public void apply() {
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1.0);
        HelloController.imageView.setEffect(grayscale);
    }
}

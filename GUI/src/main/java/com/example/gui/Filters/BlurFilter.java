package com.example.gui.Filters;

import com.example.gui.HelloController;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;

public class BlurFilter extends Filter {
    public BlurFilter() {
        super("Rozmazání");
    }

    @Override
    public void apply() {
        GaussianBlur blur = new GaussianBlur();

        HelloController.imageView.setEffect(blur);
    }
}

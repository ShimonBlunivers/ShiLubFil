package com.example.gui.Filters;

import com.example.gui.HelloController;
import javafx.scene.effect.ColorAdjust;

public class NegativeFilter extends Filter {
    public NegativeFilter() {
        super("Negativ");
    }

    @Override
    public void apply() {
        ColorAdjust colorAdjust = (ColorAdjust) HelloController.imageView.getEffect();

    }
}

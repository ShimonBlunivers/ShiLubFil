package com.example.gui.Filters;

import com.example.gui.HelloController;

public class MirrorVerticalFilter extends Filter {

    public MirrorVerticalFilter() {
        super("Vertikální Zrcadlení");
    }

    @Override
    public void apply() {
        HelloController.imageView.setScaleY(-1);
    }
}

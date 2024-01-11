package com.example.gui.Filters;

import com.example.gui.HelloController;

public class MirrorVerticalFilter extends Filter {

    public MirrorVerticalFilter() {
        super("Vertical Mirror");
    }

    @Override
    public void apply() {
        HelloController.imageView.setScaleY(-1 * HelloController.imageView.getScaleY());
    }
}

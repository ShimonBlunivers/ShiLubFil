package com.example.gui.Filters;

import com.example.gui.HelloController;

public class MirrorHorizontalFilter extends Filter {

    public MirrorHorizontalFilter() {
        super("Horizontální Zrcadlení");
    }

    @Override
    public void apply() {
        HelloController.imageView.setScaleX(-1);
    }
}

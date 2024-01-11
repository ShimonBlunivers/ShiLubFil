package com.example.gui.Filters;

import com.example.gui.HelloController;
import javafx.animation.FadeTransition;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;

public class GrayscaleFilter extends Filter {
    public GrayscaleFilter() {
        super("Grayscale");
    }
    @Override
    public void apply() {
        System.out.println(name + " clicked!");

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(44); // increase red by 50%
        HelloController.imageView.setEffect(colorAdjust);
        FadeTransition fade = new FadeTransition(Duration.seconds(2), HelloController.imageView);
        fade.setFromValue(1.0); // fully opaque
        fade.setToValue(0.0); // fully transparentfade.play();
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1.0); // fully desaturatedImageView imageView = new ImageView(new Image("path/to/image.png"));
        HelloController.imageView.setEffect(grayscale);
    }
}

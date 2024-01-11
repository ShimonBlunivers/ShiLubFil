package com.example.gui.Filters;

import com.example.gui.HelloController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.ColorAdjust;

import java.util.ArrayList;

public abstract class Filter {

    public static ArrayList<Filter> filters = new ArrayList<>();

    public static GrayscaleFilter grayscaleFilter = new GrayscaleFilter();
    public static SaturationFilter saturationFilter = new SaturationFilter();
    public static MirrorHorizontalFilter mirrorHorizontalFilter = new MirrorHorizontalFilter();
    public static MirrorVerticalFilter mirrorVerticalFilter = new MirrorVerticalFilter();




    public String name;
    public Filter(String _name) {
        name = _name;
        filters.add(this);
    }
    public abstract void apply();

    public void addToMenu(Menu menu, HelloController controller) {
        MenuItem menuItem = new MenuItem(name);

        Filter currentFilter = this;
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                apply();
                HelloController.usedFilters.add(currentFilter);
                controller.editedImageRadio.setSelected(true);
                controller.addTextToConsole("Filter (" + name + ") applied");
            }
        });

        menu.getItems().add(menuItem);
    }

    public static void resetFilters() {
        HelloController.imageView.setEffect(new ColorAdjust());
        HelloController.imageView.setScaleX(1);
        HelloController.imageView.setScaleY(1);
    }
}

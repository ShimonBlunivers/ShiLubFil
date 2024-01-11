package com.example.gui.Filters;

import com.example.gui.HelloController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;

public abstract class Filter {

    public static ArrayList<Filter> filters = new ArrayList<>();

    public static GrayscaleFilter grayscaleFilter = new GrayscaleFilter();



    public String name;
    public Filter(String _name) {
        name = _name;
        filters.add(this);
    }
    public abstract void apply();

    public void addToMenu(Menu menu, HelloController consoleController) {
        MenuItem menuItem = new MenuItem(name);
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                apply();
                consoleController.addTextToConsole("Filter (" + name + ") applied");
            }
        });

        menu.getItems().add(menuItem);
    }
}

module com.example.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.gui to javafx.fxml;
    exports com.example.gui;
}
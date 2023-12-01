module com.example.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens com.example.gui to javafx.fxml;
    exports com.example.gui;
}
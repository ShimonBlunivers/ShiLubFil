module com.example.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.media;
    requires java.desktop;
    requires javafx.swing;
    opens com.example.gui to javafx.fxml;
    exports com.example.gui;
}
module com.example.shilubi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.shilubi to javafx.fxml;
    exports com.example.shilubi;
}
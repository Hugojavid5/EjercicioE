module org.hugo.ejerciciod {
    requires javafx.controls;
    requires javafx.fxml;
    opens Model to javafx.base;
    exports com.example.ejercicioe;
    opens com.example.ejercicioe to javafx.fxml;
}

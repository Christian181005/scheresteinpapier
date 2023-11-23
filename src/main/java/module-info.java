module com.example.scheresteinpapier {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scheresteinpapier to javafx.fxml;
    exports com.example.scheresteinpapier;
}
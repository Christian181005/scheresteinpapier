module com.example.scheresteinpapier {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.scheresteinpapier to javafx.fxml;
    exports com.example.scheresteinpapier;
}
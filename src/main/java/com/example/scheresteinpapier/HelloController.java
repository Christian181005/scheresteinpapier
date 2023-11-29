package com.example.scheresteinpapier;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    public ImageView schere;

    @FXML
    public ImageView stein;

    @FXML
    public ImageView papier;

    private String eigeneAuswahl;
    private String computerAuswahl;
    private int comuterAuswahlGenerator;

    @FXML
    protected void onSchereClicked() {
        eigeneAuswahl = "schere";
    }
    @FXML
    protected void onSteinClicked() {
        eigeneAuswahl = "stein";
    }
    @FXML
    protected void onPapierClicked() {
        eigeneAuswahl = "papier";
    }
}
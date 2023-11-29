package com.example.scheresteinpapier;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.Random;

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
    Random random = new Random();

    @FXML
    protected void onSchereClicked() {
        eigeneAuswahl = "schere";
        setComuterAuswahlGenerator();
    }
    @FXML
    protected void onSteinClicked() {
        eigeneAuswahl = "stein";
        setComuterAuswahlGenerator();
    }
    @FXML
    protected void onPapierClicked() {
        eigeneAuswahl = "papier";
        setComuterAuswahlGenerator();
    }
    protected void setComuterAuswahlGenerator(){
        comuterAuswahlGenerator=random.nextInt(3) + 1;
        if (comuterAuswahlGenerator==1){
            computerAuswahl="schere";
        } else if (comuterAuswahlGenerator==2){
            computerAuswahl="Stein";
        } else{
            computerAuswahl="Papier";
        }
    }
    protected void selectWinner(){

    }
}
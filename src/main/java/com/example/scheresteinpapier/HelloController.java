package com.example.scheresteinpapier;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Random;

public class HelloController {
    @FXML
    public Label gewinner;
    @FXML
    public ImageView schere;
    @FXML
    public ImageView stein;
    @FXML
    public ImageView computeraus;
    @FXML
    public ImageView spieleraus;
    @FXML
    public ImageView papier;

    private String eigeneAuswahl;
    private String computerAuswahl;
    private int comuterAuswahlGenerator;
    Random random = new Random();
    Image imageSchere = new Image(getClass().getResourceAsStream("\"@../../../schere.png\""));

    @FXML
    protected void onSchereClicked() {
        eigeneAuswahl = "schere";
        spieleraus.setImage(imageSchere);
        setComuterAuswahlGenerator();
        selectWinner();
    }

    @FXML
    protected void onSteinClicked() {
        eigeneAuswahl = "stein";
        setComuterAuswahlGenerator();
        selectWinner();
    }

    @FXML
    protected void onPapierClicked() {
        eigeneAuswahl = "papier";
        setComuterAuswahlGenerator();
        selectWinner();
    }

    protected void setComuterAuswahlGenerator() {
        comuterAuswahlGenerator = random.nextInt(3) + 1;
        if (comuterAuswahlGenerator == 1) {
            computerAuswahl = "schere";
        } else if (comuterAuswahlGenerator == 2) {
            computerAuswahl = "Stein";
        } else {
            computerAuswahl = "Papier";
        }
        System.out.println(computerAuswahl);
        System.out.println(eigeneAuswahl);
    }

    protected void selectWinner() {
        if (eigeneAuswahl == "schere" && computerAuswahl == "papier") {
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
        } else if (eigeneAuswahl == "schere" && computerAuswahl == "stein") {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
        } else if (eigeneAuswahl == "schere" && computerAuswahl == "schere") {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
        } else if (eigeneAuswahl == "stein" && computerAuswahl == "papier") {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
        } else if (eigeneAuswahl == "stein" && computerAuswahl == "stein") {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
        } else if (eigeneAuswahl == "stein" && computerAuswahl == "schere") {
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
        } else if (eigeneAuswahl == "papier" && computerAuswahl == "papier") {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
        } else if (eigeneAuswahl == "papier" && computerAuswahl == "stein") {
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
        } else if (eigeneAuswahl == "papier" && computerAuswahl == "schere") {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
        }
    }


}
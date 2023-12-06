package com.example.scheresteinpapier;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.util.Duration;

import java.util.Random;

public class HelloController {
    @FXML
    public Label gewinner;
    @FXML
    public ImageView schere;
    @FXML
    public ImageView stein;
    @FXML
    public ImageView papier;

    @FXML
    public ImageView eigeneSchere;
    @FXML
    public ImageView eigenerStein;
    @FXML
    public ImageView eigenesPapier;

    @FXML
    public ImageView pcSchere;
    @FXML
    public ImageView pcStein;
    @FXML
    public ImageView pcPapier;

    @FXML
    public Button newGame;

    @FXML
    public ImageView computeraus;
    @FXML
    public ImageView spieleraus;
    @FXML
    public ProgressBar progressBar;

    private String eigeneAuswahl;
    private String computerAuswahl;
    private int comuterAuswahlGenerator;
    Random random = new Random();

    public void loadProgressbar() {
        progressBar.setVisible(true);
        progressBar.setProgress(0);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    progressBar.setProgress(1);
                })
        );
        timeline.setCycleCount(1);
        int numSteps = 100;
        Duration frameDuration = Duration.seconds(1).divide(numSteps);

        for (int i = 1; i <= numSteps; i++) {
            int step = i;
            timeline.getKeyFrames().add(
                    new KeyFrame(frameDuration.multiply(i), event -> {
                        double progress = (double) step / numSteps;
                        progressBar.setProgress(progress);
                    })
            );
        }

        // Play the timeline
        timeline.play();
timeline.setOnFinished(event -> {
        progressBar.setVisible(false);
        setComuterAuswahlGenerator();
        selectWinner();
        setImages();

        });

    }

    @FXML
    protected void onSchereClicked() {
        eigeneAuswahl = "schere";
        loadProgressbar();
    }

    @FXML
    protected void onSteinClicked() {
        eigeneAuswahl = "stein";
        loadProgressbar();
    }

    @FXML
    protected void onPapierClicked() {
        eigeneAuswahl = "papier";
        loadProgressbar();
    }

    protected void setComuterAuswahlGenerator() {
        comuterAuswahlGenerator = random.nextInt(3) + 1;
        if (comuterAuswahlGenerator == 1) {
            computerAuswahl = "schere";
        } else if (comuterAuswahlGenerator == 2) {
            computerAuswahl = "stein";
        } else {
            computerAuswahl = "papier";
        }
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

newGame.setVisible(true);

    }

    @FXML
    protected void setNewGame() {
        newGame.setVisible(false);
        eigeneAuswahl = "";
        computerAuswahl = "";
        comuterAuswahlGenerator = 0;
        computeraus.setVisible(true);
        spieleraus.setVisible(true);
        papier.setVisible(true);
        schere.setVisible(true);
        stein.setVisible(true);
        pcSchere.setVisible(false);
        pcStein.setVisible(false);
        pcPapier.setVisible(false);
        eigeneSchere.setVisible(false);
        eigenerStein.setVisible(false);
        eigenesPapier.setVisible(false);
        gewinner.setText("/");
        gewinner.setTextFill(Color.BLACK);
    }

    protected void setImages() {
        computeraus.setVisible(false);
        spieleraus.setVisible(false);
        papier.setVisible(false);
        schere.setVisible(false);
        stein.setVisible(false);
        if ("schere".equals(eigeneAuswahl)) {
            eigeneSchere.setVisible(true);
        } else if ("stein".equals(eigeneAuswahl)) {
            eigenerStein.setVisible(true);
        } else {
            eigenesPapier.setVisible(true);
        }

        if ("schere".equals(computerAuswahl)) {
            pcSchere.setVisible(true);
        } else if ("stein".equals(computerAuswahl)) {
            pcStein.setVisible(true);
        } else {
            pcPapier.setVisible(true);
        }
    }


}
/**
 *     @Label gewinner ...Label welches Anzeigt ob man gewonnen oder verloren hat
 *     @ImageView schere ...Bild der Schere
 *     @ImageView stein ...Bild des Steins
 *     @ImageView papier ...Bild des Papiers
 *     @ImageView eigeneSchere ...Bild des Objektes welches man ausgewählt hat
 *     @ImageView eigenerStein ...Bild des Objektes welches man ausgewählt hat
 *     @ImageView eigenesPapier ...Bild des Objektes welches man ausgewählt hat
 *     @ImageView pcSchere ...Bild des Objektes welches der Computer generierte
 *     @ImageView pcStein ...Bild des Objektes welches der Computer generierte
 *     @ImageView pcPapier ...Bild des Objektes welches der Computer generierte
 *     @Button newGame ... Beim klicken kann man erneut gegen den Computer spielen
 *     @ImageView computeraus ...Bild des Ladezeichens des Computers
 *     @ImageView spieleraus ...Bild des Ladezeichens von dem Spieler
 *     @ProgressBar progressBar ...welches man sich für ein Objekt entschieden hat lädt diese für 1 Sekunde
 *     @Label highScore ...Zeigt den persönlichen Highscore an
 *     @Label aktuellerScore ...Zeigt die aktuelle Punkteanzeige an
 *
 *     @String eigeneAuswahl ... Das Symbol welches man ausgewählt hat ausgeschrieben
 *     @String computerAuswahl ... Das Symbol welches der Computer ausgewählt hat ausgeschrieben
 *     @int comuterAuswahlGenerator ... Eine Zahl zwischen 1 und 3 wird
 *     @int scoreRn
 *     @Random random
 */

package com.example.scheresteinpapier;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

public class SchereSteinPapierController {
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
    @FXML
    public GridPane grid;
    @FXML
    public Label highScore;
    @FXML
    public Label aktuellerScore;

    private String eigeneAuswahl;
    private String computerAuswahl;
    private int comuterAuswahlGenerator;
    private int scoreRn;

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
            setHighScore();
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
            scoreRn += 1;
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl == "schere" && computerAuswahl == "stein") {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
            scoreRn = 0;
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl == "schere" && computerAuswahl == "schere") {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl == "stein" && computerAuswahl == "papier") {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
            scoreRn = 0;
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl == "stein" && computerAuswahl == "stein") {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl == "stein" && computerAuswahl == "schere") {
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
            scoreRn += 1;
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl == "papier" && computerAuswahl == "papier") {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl == "papier" && computerAuswahl == "stein") {
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
            scoreRn += 1;
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl == "papier" && computerAuswahl == "schere") {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
            scoreRn = 0;
            aktuellerScore.setText(String.valueOf(scoreRn));
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

  public void gridcolor() {

    }


    @FXML
    public void zoomSchere() {
        zoomImage(schere);
    }

    @FXML
    public void zoomOutSchere() {
        resetImageSize(schere);
    }

    @FXML
    public void zoomStein() {
        zoomStein(stein);
    }

    @FXML
    public void zoomOutStein() {
        resetSteinSize(stein);
    }

    @FXML
    public void zoomPapier() {
        zoomImage(papier);
    }

    @FXML
    public void zoomOutPapier() {
        resetImageSize(papier);
    }

    private void zoomImage(ImageView imageView) {
        double originHeight = schere.getFitHeight();
        double originWidth = schere.getFitWidth();
        double aktuelleBreite = originWidth * 1.1;
        double aktuelleHoehe = originHeight * 1.1;
        imageView.setFitWidth(aktuelleBreite);
        imageView.setFitHeight(aktuelleHoehe);
    }

    private void resetImageSize(ImageView imageView) {
        double originHeight = schere.getFitHeight();
        double originWidth = schere.getFitWidth();
        double aktuelleBreite = originWidth * (1 / 1.1);
        double aktuelleHoehe = originHeight * (1 / 1.1);
        imageView.setFitWidth(aktuelleBreite);
        imageView.setFitHeight(aktuelleHoehe);
    }

    private void zoomStein(ImageView imageView) {
        double originHeight = stein.getFitHeight();
        double originWidth = stein.getFitWidth();
        double aktuelleBreite = originWidth * 1.1;
        double aktuelleHoehe = originHeight * 1.1;
        imageView.setFitWidth(aktuelleBreite);
        imageView.setFitHeight(aktuelleHoehe);
    }

    private void resetSteinSize(ImageView imageView) {
        double originHeight = stein.getFitHeight();
        double originWidth = stein.getFitWidth();
        double aktuelleBreite = originWidth * (1 / 1.1);
        double aktuelleHoehe = originHeight * (1 / 1.1);
        imageView.setFitWidth(aktuelleBreite);
        imageView.setFitHeight(aktuelleHoehe);
    }

    private void setHighScore() {
        int score = Integer.parseInt(highScore.getText());
        if (scoreRn > score) {
            highScore.setText(String.valueOf(scoreRn));
        }
    }

}
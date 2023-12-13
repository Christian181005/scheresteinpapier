/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *           Fachrichtung Elektronik und Technische Informatik
 *----------------------------------------------------------------------------*/

/**
 * @ImageView schere ...Bild der Schere
 * @ImageView stein ...Bild des Steins
 * @ImageView papier ...Bild des Papiers
 * @ImageView eigeneSchere ...Bild des Objektes welches man ausgewählt hat
 * @ImageView eigenerStein ...Bild des Objektes welches man ausgewählt hat
 * @ImageView eigenesPapier ...Bild des Objektes welches man ausgewählt hat
 * @ImageView pcSchere ...Bild des Objektes welches der Computer generierte
 * @ImageView pcStein ...Bild des Objektes welches der Computer generierte
 * @ImageView pcPapier ...Bild des Objektes welches der Computer generierte
 * @ImageView computeraus ...Bild des Ladezeichens des Computers
 * @ImageView spieleraus ...Bild des Ladezeichens von dem Spieler
 * @Button newGame ... Beim klicken, kann man erneut gegen den Computer spielen
 * @ProgressBar progressBar ...welches man sich für ein Objekt entschieden hat, lädt diese für 1 Sekunde
 * @Label highScore ...Zeigt den persönlichen Highscore an
 * @Label aktuellerScore ...Zeigt die aktuelle Punkteanzeige an
 * @Label gewinner ...Label welches Anzeigt ob man gewonnen oder verloren hat
 * @String eigeneAuswahl ... Das Symbol welches man ausgewählt hat ausgeschrieben
 * @String computerAuswahl ... Das Symbol welches der Computer ausgewählt hat ausgeschrieben
 * @int comuterAuswahlGenerator ... Eine Zahl zwischen 1 und 3 wird
 * @int scoreRn ... Wie viele Punkte man aktuell hat als int abgespeichert
 * @Random random ... Generiert die Auswahl des Computers
 */

package com.example.scheresteinpapier;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
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
    public Label highScore;
    @FXML
    public Label aktuellerScore;

    private String eigeneAuswahl = "";
    private String computerAuswahl = "";
    private int comuterAuswahlGenerator = 0;
    private int scoreRn = 0;

    Random random = new Random();

    public void loadProgressbar() { //Funktion zum Laden der Progressbar in 1 Sekunde
        progressBar.setVisible(true);
        progressBar.setProgress(0);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> progressBar.setProgress(1)));
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

        // Play timeline
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

    protected void setComuterAuswahlGenerator() { //Zufallszahl zwischen 1 und 3 wird generiert
        comuterAuswahlGenerator = random.nextInt(3) + 1;
        if (comuterAuswahlGenerator == 1) {
            computerAuswahl = "schere";
        } else if (comuterAuswahlGenerator == 2) {
            computerAuswahl = "stein";
        } else {
            computerAuswahl = "papier";
        }
    }

    protected void selectWinner() { //Auswertung wer gewonnen hat
        if (eigeneAuswahl.equals("schere") && computerAuswahl.equals("papier")) {
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
            scoreRn += 1;
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl.equals("schere") && computerAuswahl.equals("stein")) {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
            scoreRn = 0;
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl.equals("schere") && computerAuswahl.equals("schere")) {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl.equals("stein") && computerAuswahl.equals("papier")) {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
            scoreRn = 0;
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl.equals("stein") && computerAuswahl.equals("stein")) {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl.equals("stein") && computerAuswahl.equals("schere")) {
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
            scoreRn += 1;
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl.equals("papier") && computerAuswahl.equals("papier")) {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl.equals("papier") && computerAuswahl.equals("stein")) {
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
            scoreRn += 1;
            aktuellerScore.setText(String.valueOf(scoreRn));
        } else if (eigeneAuswahl.equals("papier") && computerAuswahl.equals("schere")) {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
            scoreRn = 0;
            aktuellerScore.setText(String.valueOf(scoreRn));
        }

        newGame.setVisible(true);

    }

    @FXML
    protected void setNewGame() { //Alles wird zurückgesetzt
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

    protected void setImages() { //Zeigt statt dem Ladesymbol das gewählte Symbol aus
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

    /*Folgende 10 Methoden sorgen dafür dass wenn man über ein Symbol mit der Maus fährt,
    dass diese größer werden und wieder kleiner wenn, man sie verlässt*/
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

    //Wenn man mehr Punkte als der Highscore hat, wird dieser zur aktuellen Punktezahl
    private void setHighScore() {
        int score = Integer.parseInt(highScore.getText());
        if (scoreRn > score) {
            highScore.setText(String.valueOf(scoreRn));
        }
    }

}
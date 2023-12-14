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
 * @String szEigeneAuswahl ... Das Symbol welches man ausgewählt hat ausgeschrieben
 * @String szComputerAuswahl ... Das Symbol welches der Computer ausgewählt hat ausgeschrieben
 * @int nComuterAuswahlGenerator ... Eine Zahl zwischen 1 und 3 wird
 * @int nScoreRn ... Wie viele Punkte man aktuell hat als int abgespeichert
 * @Random random ... Generiert die Auswahl des Computers
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
import javafx.scene.control.ProgressIndicator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.File;
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
    public ImageView brunnen;
    @FXML
    public ImageView pcSchere;
    @FXML
    public ImageView pcStein;
    @FXML
    public ImageView pcPapier;
    @FXML
    public ImageView pcBrunnen;
    @FXML ImageView eigenerBrunnen;
    @FXML
    public Button newGame;
    @FXML
    public ProgressBar progressBar;
    @FXML
    public GridPane grid;
    @FXML
    public Label highScore;
    @FXML
    public Label aktuellerScore;
    @FXML
    private ProgressIndicator progressIndicatorPlayer;
    @FXML
    private ProgressIndicator progressIndicatorPC;
    private String szEigeneAuswahl = "";
    private String szComputerAuswahl = "";
    private int nComuterAuswahlGenerator = 0;
    private int nScoreRn = 0;

    Random random = new Random();


    String path1 = "src/main/resources/winsound.mp3";
    File file1 = new File(path1);
    javafx.scene.media.Media media1 = new javafx.scene.media.Media(file1.toURI().toString());
    javafx.scene.media.MediaPlayer mediaPlayer1 = new javafx.scene.media.MediaPlayer(media1);


    String path2 = "src/main/resources/losesound.mp3";
    File file2 = new File(path2);
    javafx.scene.media.Media media2 = new javafx.scene.media.Media(file2.toURI().toString());
    javafx.scene.media.MediaPlayer mediaPlayer2 = new javafx.scene.media.MediaPlayer(media2);

    public void initialize() {
        // Set the progress value to -1.0
        progressIndicatorPlayer.setProgress(-1.0);
        progressIndicatorPC.setProgress(-1.0);
    }

    private void rmvImageChoose(){
        papier.setVisible(false);
        schere.setVisible(false);
        stein.setVisible(false);
        brunnen.setVisible(false);
    }
    public void loadProgressbar() {
        //Funktion zum Laden der Progressbar in 1 Sekunde
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
            progressIndicatorPC.setVisible(false);
            progressIndicatorPlayer.setVisible(false);
        });

    }
    private void disableImages(){
        brunnen.setDisable(true);
        stein.setDisable(true);
        schere.setDisable(true);
        papier.setDisable(true);
    }

    @FXML
    protected void onWelllicked() {
        szEigeneAuswahl = "well";
        disableImages();
        loadProgressbar();
    }

    @FXML
    protected void onSchereClicked() {
        szEigeneAuswahl = "schere";
        disableImages();
        loadProgressbar();
    }

    @FXML
    protected void onSteinClicked() {
        szEigeneAuswahl = "stein";
        disableImages();
        loadProgressbar();
    }

    @FXML
    protected void onPapierClicked() {
        szEigeneAuswahl = "papier";
        disableImages();
        loadProgressbar();
    }

    protected void setComuterAuswahlGenerator() { //Zufallszahl zwischen 1 und 3 wird generiert
        nComuterAuswahlGenerator = random.nextInt(4) + 1;
        if (nComuterAuswahlGenerator == 1) {
            szComputerAuswahl = "schere";
        } else if (nComuterAuswahlGenerator == 2) {
            szComputerAuswahl = "stein";
        } else if (nComuterAuswahlGenerator == 3) {
            szComputerAuswahl = "papier";
        } else {
            szComputerAuswahl = "well";
        }
    }

    protected void selectWinner() { //Auswertung wer gewonnen hat
        if (szEigeneAuswahl.equals("schere") && szComputerAuswahl.equals("papier")) {
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
            nScoreRn += 1;
            aktuellerScore.setText(String.valueOf(nScoreRn));
            onwin();
        } else if (szEigeneAuswahl.equals("schere") && szComputerAuswahl.equals("stein")) {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
            nScoreRn = 0;
            aktuellerScore.setText(String.valueOf(nScoreRn));
            onlose();
        } else if (szEigeneAuswahl.equals("schere") && szComputerAuswahl.equals("schere")) {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
            aktuellerScore.setText(String.valueOf(nScoreRn));
        } else if (szEigeneAuswahl.equals("stein") && szComputerAuswahl.equals("papier")) {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
            nScoreRn = 0;
            aktuellerScore.setText(String.valueOf(nScoreRn));
            onlose();
        } else if (szEigeneAuswahl.equals("stein") && szComputerAuswahl.equals("stein")) {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
            aktuellerScore.setText(String.valueOf(nScoreRn));
        } else if (szEigeneAuswahl.equals("stein") && szComputerAuswahl.equals("schere")) {
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
            nScoreRn += 1;
            aktuellerScore.setText(String.valueOf(nScoreRn));
            onwin();
        } else if (szEigeneAuswahl.equals("papier") && szComputerAuswahl.equals("papier")) {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
            aktuellerScore.setText(String.valueOf(nScoreRn));
        } else if (szEigeneAuswahl.equals("papier") && szComputerAuswahl.equals("stein")) {
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
            nScoreRn += 1;
            aktuellerScore.setText(String.valueOf(nScoreRn));
            onwin();
        } else if (szEigeneAuswahl.equals("papier") && szComputerAuswahl.equals("schere")) {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
            nScoreRn = 0;
            aktuellerScore.setText(String.valueOf(nScoreRn));
            onlose();
        } else if (szEigeneAuswahl.equals("well") && szComputerAuswahl.equals("well")) {
            gewinner.setText("Unentschieden");
            gewinner.setTextFill(Color.GRAY);
            aktuellerScore.setText(String.valueOf(nScoreRn));
        } else if (szEigeneAuswahl.equals("well") && szComputerAuswahl.equals("stein")) {
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
            nScoreRn += 1;
            aktuellerScore.setText(String.valueOf(nScoreRn));
            onwin();
        } else if (szEigeneAuswahl.equals("well") && szComputerAuswahl.equals("papier")) {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
            nScoreRn = 0;
            aktuellerScore.setText(String.valueOf(nScoreRn));
            onlose();
        } else if (szEigeneAuswahl.equals("well") && szComputerAuswahl.equals("schere")){
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
            nScoreRn += 1;
            aktuellerScore.setText(String.valueOf(nScoreRn));
            onwin();
        } else if (szComputerAuswahl.equals("well") && szEigeneAuswahl.equals("stein")) {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
            nScoreRn = 0;
            aktuellerScore.setText(String.valueOf(nScoreRn));
            onlose();
        } else if (szComputerAuswahl.equals("well") && szEigeneAuswahl.equals("papier")){
            gewinner.setText("Gewonnen");
            gewinner.setTextFill(Color.GREEN);
            nScoreRn += 1;
            aktuellerScore.setText(String.valueOf(nScoreRn));
            onwin();
        } else if (szComputerAuswahl.equals("well") && szEigeneAuswahl.equals("schere")) {
            gewinner.setText("Verloren");
            gewinner.setTextFill(Color.RED);
            nScoreRn = 0;
            aktuellerScore.setText(String.valueOf(nScoreRn));
            onlose();
        }

        newGame.setVisible(true);

    }

    void onwin() {
        mediaPlayer1.setVolume(1.00);
        mediaPlayer1.play();
    }

    void onlose() {
        mediaPlayer2.setVolume(1.00);
        mediaPlayer2.play();
    }


    @FXML
    protected void setNewGame() { //Alles wird zurückgesetzt
        newGame.setVisible(false);
        szEigeneAuswahl = "";
        szComputerAuswahl = "";
        nComuterAuswahlGenerator = 0;
        //  computeraus.setVisible(true);
        //  spieleraus.setVisible(true);
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
        mediaPlayer1.stop();
        mediaPlayer2.stop();
        progressIndicatorPC.setVisible(true);
        progressIndicatorPlayer.setVisible(true);
        eigenerBrunnen.setVisible(false);
        pcBrunnen.setVisible(false);
        brunnen.setVisible(true);
        brunnen.setDisable(false);
        stein.setDisable(false);
        schere.setDisable(false);
        papier.setDisable(false);
    }

    protected void setImages() { //Zeigt statt dem Ladesymbol das gewählte Symbol aus
        rmvImageChoose();
        if ("schere".equals(szEigeneAuswahl)) {
            eigeneSchere.setVisible(true);
        } else if ("stein".equals(szEigeneAuswahl)) {
            eigenerStein.setVisible(true);
        } else  if ("papier".equals(szEigeneAuswahl)){
            eigenesPapier.setVisible(true);
        } else {
            eigenerBrunnen.setVisible(true);
        }

        if ("schere".equals(szComputerAuswahl)) {
            pcSchere.setVisible(true);
        } else if ("stein".equals(szComputerAuswahl)) {
            pcStein.setVisible(true);
        } else if ("papier".equals(szComputerAuswahl)){
            pcPapier.setVisible(true);
        } else {
            pcBrunnen.setVisible(true);
        }
    }

    public void gridcolor() {

    }


    /*Folgende 10 Methoden sorgen dafür dass wenn man über ein Symbol mit der Maus fährt,
    dass diese größer werden und wieder kleiner wenn, man sie verlässt*/
    @FXML
    public void zoomWell() {
        zoomImage(brunnen);
    }

    @FXML
    public void zoomOutWell() {
        resetImageSize(brunnen);
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
        zoomImage(stein);
    }

    @FXML
    public void zoomOutStein() {
        resetImageSize(stein);
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
        double originHeight = imageView.getFitHeight();
        double originWidth = imageView.getFitWidth();
        double aktuelleBreite = originWidth * 1.1;
        double aktuelleHoehe = originHeight * 1.1;
        imageView.setFitWidth(aktuelleBreite);
        imageView.setFitHeight(aktuelleHoehe);
    }

    private void resetImageSize(ImageView imageView) {
        double originHeight = imageView.getFitHeight();
        double originWidth = imageView.getFitWidth();
        double aktuelleBreite = originWidth * (1 / 1.1);
        double aktuelleHoehe = originHeight * (1 / 1.1);
        imageView.setFitWidth(aktuelleBreite);
        imageView.setFitHeight(aktuelleHoehe);
    }

    //Wenn man mehr Punkte als der Highscore hat, wird dieser zur aktuellen Punktezahl
    private void setHighScore() {
        int score = Integer.parseInt(highScore.getText());
        if (nScoreRn > score) {
            highScore.setText(String.valueOf(nScoreRn));
        }
    }

    @FXML
    private void exportLabelValue() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("HighscoreTable.txt"))) {
            writer.write(highScore.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
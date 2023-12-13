/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *           Fachrichtung Elektronik und Technische Informatik
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Christian Domes, Manuel Pichler
 * @date    : $6.12.2023$
 *
 * @details
 *   Schere Stein Papier indem man gegen einen Computer spielt, dieser generiert
 *   eine zufällige auswahl und nach 1 Sekunde sieht man ob man gewonnen oder verloren hat
 *   Zusätzlich gibt es einen Highscore und eine Anzeige die einem zeigt wie viele Punkte
 *   man aktuell hat.
 *
 */

package com.example.scheresteinpapier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SchereSteinPapierApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchereSteinPapierApplication.class.getResource("SchereSteinPapier-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 550);
        stage.setTitle("Schere Stein Papier");
        stage.setScene(scene);
        stage.show();


        String path = "src/main/resources/bg_music.mp3";
        File file = new File(path);
        javafx.scene.media.Media media = new javafx.scene.media.Media(file.toURI().toString());
        javafx.scene.media.MediaPlayer mediaPlayer = new javafx.scene.media.MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
        mediaPlayer.play();

    }

    public static void main(String[] args) {
        launch();
    }
}
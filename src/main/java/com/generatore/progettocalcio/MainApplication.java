package com.generatore.progettocalcio;

import com.generatore.progettocalcio.model.Giocatore;
import com.generatore.progettocalcio.model.Squadra;
import com.generatore.progettocalcio.util.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setStage(stage);
        SceneManager.switchTo("/view/hello-view.fxml");
    }

    public static void main(String[] args) { launch();}
}
package com.generatore.progettocalcio.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {

    private static Stage primaryStage;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void switchTo(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();

            primaryStage.setScene(new Scene(root, 980, 768));
            primaryStage.setTitle("Team Generator");
            primaryStage.getIcons().add(new Image(Objects.requireNonNull(SceneManager.class.getResourceAsStream("/img/favico.png"))));

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

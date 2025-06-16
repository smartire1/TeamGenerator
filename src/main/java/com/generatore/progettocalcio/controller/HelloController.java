package com.generatore.progettocalcio.controller;

import com.generatore.progettocalcio.model.Giocatore;
import com.generatore.progettocalcio.model.Squadra;
import com.generatore.progettocalcio.model.TeamGen;
import com.generatore.progettocalcio.util.PlayerLoader;
import com.generatore.progettocalcio.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HelloController {
    @FXML
    public ListView<String> ListViewSquadre;

    private Squadra s1;
    private Giocatore g1;
    private ArrayList<Giocatore> listaGiocatori;


    public void UpdateList() {
        ListViewSquadre.getItems().clear();
        ArrayList<Squadra> squadre = DataManager.getInstance().getSquadre();
        for(Squadra s : squadre) {
            ListViewSquadre.getItems().add(s.getNome() + " - " + s.getNumGiocatori());
            System.out.println(s.toString());
        }
    }

    @FXML
    public void ButtonGenera() {
        ArrayList<Giocatore> giocatori = DataManager.getInstance().getGiocatori();
        if(giocatori.size() < 10) {
            showAlert("Numero giocatori insufficiente");
        }
        else {
            TeamGen.Genera(4);
            UpdateList();
        }
    }

    @FXML
    public void resettaButton() {
        DataManager.getInstance().resetSquadre();
        UpdateList();
    }

    @FXML
    private void apriGestionePlayer(ActionEvent event) {
        SceneManager.switchTo("/view/ManagePlayer.fxml");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attenzione");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onVisualizzaSquadreClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TeamViewController.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Visualizza Squadre");
            stage.setScene(new Scene(root, 1250, 600));
            stage.getIcons().add(new Image(Objects.requireNonNull(SceneManager.class.getResourceAsStream("/img/favico.png"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
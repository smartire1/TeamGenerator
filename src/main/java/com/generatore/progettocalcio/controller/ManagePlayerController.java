package com.generatore.progettocalcio.controller;

import com.generatore.progettocalcio.model.Giocatore;
import com.generatore.progettocalcio.model.Squadra;
import com.generatore.progettocalcio.controller.DataManager;
import com.generatore.progettocalcio.util.PlayerLoader;
import com.generatore.progettocalcio.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class ManagePlayerController {

    @FXML
    public ListView<String> ListViewGiocaotri;
    private Giocatore g1;
    private ArrayList<Giocatore> listaGiocatori;

    @FXML
    public void initialize() {
        // 1. Carica i giocatori esistenti dal DataManager
        listaGiocatori = DataManager.getInstance().getGiocatori();
        // 3. Aggiorna la ListView con tutti i giocatori
        updateListView();
    }

    private void updateListView() {
        ListViewGiocaotri.getItems().clear();
        for (Giocatore g : DataManager.getInstance().getGiocatori()) {
            ListViewGiocaotri.getItems().add(g.getCodiceFiscale() + " - " +
                    g.getNome() + " - " +
                    g.getCognome() + " - " +
                    g.getEta() + " - " +
                    g.getRuolo());
        }
    }

    @FXML
    public void MainScene(ActionEvent event) {
        SceneManager.switchTo("/view/hello-view.fxml");
    }

    @FXML
    public void LoadJson() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona un file JSON");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("File JSON", "*.json")
        );

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            ArrayList<Giocatore> giocatori = filtraDuplicati(PlayerLoader.loadPlayersFromFile(selectedFile));

            if (!giocatori.isEmpty()) {
                // Aggiungi al DataManager
                com.generatore.progettocalcio.controller.DataManager.getInstance().addGiocatori(giocatori);

                // Aggiorna la ListView
                updateListView();

                System.out.println("Caricati " + giocatori.size() + " giocatori");
                giocatori.forEach(g -> System.out.println(g.getNome() + " " + g.getCognome()));
            }
        }
    }

    private ArrayList<Giocatore> filtraDuplicati(ArrayList<Giocatore> nuoviGiocatori) {
        ArrayList<Giocatore> giocatoriUnici = new ArrayList<>();
        Set<String> codiciFiscaliEsistenti = DataManager.getInstance()
                .getGiocatori()
                .stream()
                .map(Giocatore::getCodiceFiscale)
                .collect(Collectors.toSet());

        for (Giocatore g : nuoviGiocatori) {
            if (!codiciFiscaliEsistenti.contains(g.getCodiceFiscale())) {
                giocatoriUnici.add(g);
                codiciFiscaliEsistenti.add(g.getCodiceFiscale()); // Aggiungi al set per controlli successivi
            } else {
                System.out.println("Scartato duplicato: " + g.getCodiceFiscale());
            }
        }

        return giocatoriUnici;
    }
}
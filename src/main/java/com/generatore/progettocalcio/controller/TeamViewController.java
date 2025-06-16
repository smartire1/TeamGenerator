package com.generatore.progettocalcio.controller;

import com.generatore.progettocalcio.model.Giocatore;
import com.generatore.progettocalcio.model.Squadra;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;

public class TeamViewController {
    @FXML
    private TilePane container;

    @FXML
    public void initialize() {
        // Applica lo stile al container
        container.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        container.getStyleClass().add("team-container");

        ArrayList<Squadra> squadre = DataManager.getInstance().getSquadre();

        for (Squadra s : squadre) {
            Collections.shuffle(s.getListaGiocatori());

            // Titolo squadra
            Label titolo = new Label(s.getNome());
            titolo.getStyleClass().add("team-title");

            // Lista giocatori
            VBox giocatoriBox = new VBox();
            giocatoriBox.getStyleClass().add("players-list");

            for (Giocatore g : s.getListaGiocatori()) {
                Label l = new Label("• " + g.getNome() + " " + g.getCognome() + " (" + g.getRuolo() + ")");
                l.getStyleClass().add("player-item");

                // Aggiungi classe specifica per il ruolo (opzionale)
                l.getStyleClass().add(g.getRuolo().toLowerCase());

                giocatoriBox.getChildren().add(l);
            }

            // Card container
            VBox card = new VBox(titolo, giocatoriBox);
            card.getStyleClass().add("team-card");
            card.setSpacing(10);

            container.getChildren().add(card);
        }
    }

    private static String getString() {
        return "com/generatore/progettocalcio/resources";
    }
}
package com.generatore.progettocalcio.util;

import com.generatore.progettocalcio.model.Giocatore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.util.ArrayList;

public class PlayerLoader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static ArrayList<Giocatore> loadPlayersFromFile(File jsonFile) {
        try {
            // Usa TypeReference con ArrayList invece di List
            return mapper.readValue(jsonFile, new TypeReference<ArrayList<Giocatore>>() {});
        } catch (Exception e) {
            System.err.println("Errore durante la lettura del file JSON:");
            e.printStackTrace();
            return new ArrayList<>(); // Ritorna ArrayList vuoto in caso di errore
        }
    }
}
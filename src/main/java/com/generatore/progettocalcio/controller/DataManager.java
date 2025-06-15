package com.generatore.progettocalcio.controller;

import com.generatore.progettocalcio.model.Giocatore;
import com.generatore.progettocalcio.model.Squadra;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DataManager {
    private static DataManager instance;
    private ArrayList<Giocatore> giocatori = new ArrayList<>();
    private ArrayList<Squadra> squadre = new ArrayList<>();

    private DataManager() {}

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    public ArrayList<Giocatore> getTessrati(){
        return giocatori.stream().filter(Giocatore::getTesserato).collect(Collectors.toCollection(ArrayList::new));                                              //  Ritorna tutti i giocatori tesserati
    }

    public ArrayList<Giocatore> getPortieri(){
        return giocatori.stream().filter(g -> g.getRuolo().equals("portiere")).collect(Collectors.toCollection(ArrayList::new));                        //  Ritorna tutti i giocatori over 35 NON tesserati
    }

    public ArrayList<Giocatore> getPlayer(){
        return giocatori.stream().filter(g -> !g.getTesserato() && !g.getRuolo().equals("portiere")).collect(Collectors.toCollection(ArrayList::new));  //  Ritorna tutti i giocatori con score (non tesserati)
    }

    public void addGiocatori(ArrayList<Giocatore> nuoviGiocatori) {
        giocatori.addAll(nuoviGiocatori);
    }

    public void addGiocatore(Giocatore giocatore) {
        giocatori.add(giocatore);
    }

    public ArrayList<Squadra> getSquadre() {
        return squadre;
    }

    public void addSquadre(ArrayList<Squadra> nuoveSquadre) {
        squadre.addAll(nuoveSquadre);
    }

    public void addSquadra(Squadra squadra) {
        squadre.add(squadra);
    }
}
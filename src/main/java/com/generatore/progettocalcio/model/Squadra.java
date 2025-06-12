package com.generatore.progettocalcio.model;

import java.util.ArrayList;
import java.util.ArrayList;


public class Squadra {
    private String nome;
    private int numGiocatori;
    private int score;
    private ArrayList<Giocatore> listaGiocatori = new ArrayList<>();

    public Squadra(String nome) {
        this.nome = nome;
        this.numGiocatori = 0;
        this.score = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getNumGiocatori() {
        return numGiocatori;
    }

    public ArrayList<Giocatore> getListaGiocatori() {
        return listaGiocatori;
    }

    public int getScore() {
        return score;
    }

    public void inserisciGiocatore(Giocatore giocatore) {
        listaGiocatori.add(giocatore);
        this.numGiocatori += 1;
        this.score += giocatore.getPunteggio();
    }

    public void rimuoviGiocatore(String cf) {
        listaGiocatori.removeIf(g -> g.getCodiceFiscale().equals(cf));
    }

    int getNumRole(String role){
        int num = (int) listaGiocatori.stream().filter(g -> g.getRuolo().equals(role)).count();
        return num;
    }

    @Override
    public String toString() {
        return "Squadra {" +
                "nome='" + nome + '\'' +
                ", numGiocatori=" + numGiocatori + '\'' +
                ", Score=" + score + '\'' + "\n" +
                listaGiocatori.toString()  +
                '}';
    }
}

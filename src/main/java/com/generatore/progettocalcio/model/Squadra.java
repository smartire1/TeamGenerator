package com.generatore.progettocalcio.model;

import java.util.ArrayList;
import java.util.ArrayList;


public class Squadra {
    private String nome;
    private int numGiocatori;
    private int numA;
    private int numB;
    private int numC;
    private int numD;
    private int score;
    private ArrayList<Giocatore> listaGiocatori = new ArrayList<>();

    public Squadra(String nome) {
        this.nome = nome;
        this.numGiocatori = 0;
        this.score = 0;
        this.numA = 0;
        this.numB = 0;
        this.numC = 0;
        this.numD = 0;
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
        if(giocatore.getTesserato()) {
            numA++;
        }
        else {
            switch (giocatore.getEsperienza()) {
                case"principiante":
                    numD++;
                    break;
                case"intermedio":
                    numC++;
                    break;
                case"avanzato":
                    numB++;
                    break;
            }
        }
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
                ", Num A= " + numA + '\'' +
                ", num B= " + numB + '\'' +
                ", num C= " + numC + '\'' +
                ", num D= " + numD + '\'' +
                ", Score=" + score + '\'' + "\n" +
                listaGiocatori.toString()  +
                '}';
    }
}

package com.generatore.progettocalcio.model;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;


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

    public Giocatore rimuoviGiocatore(String cf) {
        for(Giocatore g: listaGiocatori) {
            if(g.getCodiceFiscale().equals(cf)) {
                listaGiocatori.remove(g);
                this.numGiocatori--;
                this.score -= g.getPunteggio();
                return g;
            }
        }
        return null;
    }

    int getNumRole(String role){
        int num = (int) listaGiocatori.stream().filter(g -> g.getRuolo().equals(role)).count();
        return num;
    }

    public int getNumB(String s) {
        int numB = 0;
        for(Giocatore g: listaGiocatori) {
            if(g.getRuolo().equals(s) && g.getEsperienza().equals("avanzato"))
                numB++;
        }
        return numB;
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

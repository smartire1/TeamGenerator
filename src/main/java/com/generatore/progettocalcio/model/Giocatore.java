package com.generatore.progettocalcio.model;

public class Giocatore {
    private String CodiceFiscale;
    private String Nome;
    private String Cognome;
    private int Eta;
    private String Ruolo;
    private Boolean Tesserato;
    private String Esperienza;

    public Giocatore(String codiceFiscale, String nome, String cognome, int eta, String ruolo, Boolean tesserato, String esperienza) {
        CodiceFiscale = codiceFiscale;
        Nome = nome;
        Cognome = cognome;
        Eta = eta;
        Ruolo = ruolo;
        Tesserato = tesserato;
        Esperienza = esperienza;
    }

    public Giocatore() {
    }

    public String getCodiceFiscale() {
        return CodiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        CodiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public int getEta() {
        return Eta;
    }

    public void setEta(int eta) {
        Eta = eta;
    }

    public String getRuolo() {
        return Ruolo;
    }

    public void setRuolo(String ruolo) {
        Ruolo = ruolo;
    }

    public Boolean getTesserato() {
        return Tesserato;
    }

    public void setTesserato(Boolean tesserato) {
        Tesserato = tesserato;
    }

    public String getEsperienza() {
        return Esperienza;
    }

    int getPunteggio() {
        if (Tesserato) return 10;
        if (Eta > 34 && !Tesserato) return 1;
        return switch (Esperienza) {
            case "principiante" -> 2;
            case "intermedio" -> 5;
            case "avanzato" -> 7;
            default -> 999;
        };
    }

    public void setEsperienza(String esperienza) {
        Esperienza = esperienza;
    }

    @Override
    public String toString() {
        return "\tGiocatore{" + "CF: " + CodiceFiscale + ", nome: " + Nome + ", Cognome: " + Cognome + ", eta: " + Eta + ", Ruolo: " + Ruolo + ", Tesserato: " + Tesserato + "}\n";
    }
}

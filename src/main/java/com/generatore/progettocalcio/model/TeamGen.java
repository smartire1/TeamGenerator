package com.generatore.progettocalcio.model;

import com.generatore.progettocalcio.controller.DataManager;

import javax.management.relation.Role;
import java.util.*;

public class TeamGen{
    public static void Genera(int Nteam){

        //Creazione delle N squadre
        ArrayList<Squadra> squadre = new ArrayList<>();
        for(int i=0; i<Nteam; i++) {
            squadre.add(new Squadra("Squadra " + (i+1)));
        }

        //indice scelta squadra
        int indexSquadra = 0;

        //Recupero dei giocatori dal DataManager
        ArrayList<Giocatore> Tesserati = DataManager.getInstance().getTessrati();
        ArrayList<Giocatore> Over35 = DataManager.getInstance().getOver35();
        ArrayList<Giocatore> Player = DataManager.getInstance().getPlayer();

        //primo shuffle
        Collections.shuffle(Tesserati);
        Collections.shuffle(Over35);
        Collections.shuffle(Player);

        //defizione dei ruoli
        String[] ruoli = {"portiere","difensore", "centrocampista", "attaccante"};

        //Mappa conteggio numero player per squadra
        int[] conteggioSquadre = new int[Nteam];

        //Mappa per la divisione dei giocatori per ruolo
        Map<String, ArrayList<Giocatore>> GiocatoriPerRuolo = new HashMap<>();

        for(String r: ruoli) {
            GiocatoriPerRuolo.put(r, new ArrayList<Giocatore>());
        }

        for(Giocatore g: Tesserati){
            GiocatoriPerRuolo.get(g.getRuolo()).add(g);
        }

        //Cuore algoritmo, distribuzione bilanciata
        for(String r: ruoli) {

            //Recupero i giocatori solo del ruolo attuale e li mescolo ulteriormente
            ArrayList<Giocatore> ruoloAttuale = GiocatoriPerRuolo.get(r);
            Collections.shuffle(ruoloAttuale);

            // Ordina squadre per numero complessivo di giocatori (priorità alle squadre con meno giocatori)
            ArrayList<Squadra> squadreOrdinate = new ArrayList<>(squadre);
            squadreOrdinate.sort(Comparator.comparingInt(s -> conteggioSquadre[squadre.indexOf(s)]));

            indexSquadra = 0;
            for(Giocatore g : ruoloAttuale){
                Squadra squadraScelta = squadreOrdinate.get(indexSquadra % Nteam);
                squadraScelta.inserisciGiocatore(g);

                //Aggiorno il conteggio dei giocatori nella squadra
                int idx = squadre.indexOf(squadraScelta);
                conteggioSquadre[idx]++;

                indexSquadra++;

            }
        }

        //Inserimento under 35 per livello

/*
        String[] Livelli = {"avanzato", "intermedio","principiante"};

        Map<String, ArrayList<Giocatore>> U35 = new HashMap<>();
        for(String s: Livelli) {
            U35.put(s, new ArrayList<>());
        }

        for(Giocatore g: Player) {
            U35.get(g.getEsperienza()).add(g);
        }

        if(Player.isEmpty()) System.out.println("Lista U35 vuota");
        else {
            System.out.println("Lista U35: " + Player.size());
    //        System.out.println("mappa U35: " + U35.get("attaccante").size());
            for(Giocatore g12: Player) {
                System.out.println(g12.getNome());
            }
        }

        //per ogni ruolo
        for(String s: ruoli) {
            ArrayList<Giocatore> RoleExperience = new ArrayList<>();


            System.out.println(s + RoleExperience.size() + "\n");
//            RoleExperience.toString();
//
//            //per ogni giocatore del ruolo corrente
//            if(RoleExperience == null) {
//                for (Giocatore gg : RoleExperience) {
//                    squadre.sort(Comparator.comparingInt(Squadra::getScore));
//                    squadre.stream().filter(sq -> sq.getScore() == squadre.get(0).getScore()).sorted(Comparator.comparingInt(sq -> sq.getNumRole(s)));
//                    int indexTemp = 0;
//                    squadre.get(indexTemp).inserisciGiocatore(gg);
//                }
//            }
        }
*/
        //Inserimento RR U35

        for(String r : ruoli){
            for(Giocatore g : Player) {
                if(g.getRuolo().equals(r)) {
                    squadre.get(indexSquadra % Nteam).inserisciGiocatore(g);
                    indexSquadra++;
                }
            }
        }
        //inserimento portieri
        ArrayList<Giocatore> portieri = DataManager.getInstance().getPortieri();
        for(Giocatore g : portieri) {
            squadre.get(indexSquadra % Nteam).inserisciGiocatore(g);
            indexSquadra++;
        }
        DataManager.getInstance().addSquadre(squadre);
    }
}








//public class TeamGen {
//
//    public static void Genera(int Nteam) {
//        int index = 0;
//
//        ArrayList<Squadra> squadre = new ArrayList<>();
//        for(int i = 1; i <= Nteam; i++) {
//            squadre.add(new Squadra("Squadra " + i));
//        }
//
//        String[] ruoli = {"portiere", "difensore", "centrocampista", "attaccante"};
//        ArrayList<Giocatore> Tesserati = DataManager.getInstance().getTessrati();
//        ArrayList<Giocatore> Over35 = DataManager.getInstance().getOver35();
//        ArrayList<Giocatore> Livello = DataManager.getInstance().getPlayer();
//
//        for(String r : ruoli){
//            for(Giocatore g : Tesserati) {
//                if(g.getRuolo().equals(r)) {
//                    squadre.get(index % Nteam).inserisciGiocatore(g);
//                    System.out.println(r + " aggiunto a squadra -> " + index % Nteam);
//                    index++;
//                }
//            }
//            //Collections.shuffle(squadre);
//            System.out.println("\n");
//        }
//
//        for(String r : ruoli){
//            for(Giocatore g : Over35) {
//                if(g.getRuolo().equals(r)) {
//                    squadre.get(index % Nteam).inserisciGiocatore(g);
//                    System.out.println(r + " aggiunto a squadra -> " + index % Nteam);
//                    index++;
//                }
//            }
//            //Collections.shuffle(squadre);
//            System.out.println("\n");
//        }
//
//        DataManager.getInstance().addSquadre(squadre);
//    }
//}

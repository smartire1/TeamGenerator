package com.generatore.progettocalcio.model;

import com.generatore.progettocalcio.controller.DataManager;

import javax.management.relation.Role;
import java.util.*;
import java.util.stream.Collectors;

public class TeamGen{
    public static void Genera(int Nteam){

        //Creazione delle N squadre
        String[] Teams = {"Real Casola", " Casola Paris", "Casola Reds", "Casola Celtic"};
        Collections.shuffle(Arrays.asList(Teams));
        ArrayList<Squadra> squadre = new ArrayList<>();
        for(String s: Teams) {
            squadre.add(new Squadra(s));
        }

        Giocatore g1 = new Giocatore("BLDFLV93D03F839R", "Baldo","Folvini",32,"difensore",false,"avanzato");
        Giocatore g2 = new Giocatore("RCCVCN99L01F839X", "Vincenzo","Ricciardi",25,"attaccante",true,"avanzato");
        Giocatore g3 = new Giocatore("DNNGPP94B11F839J", "Giuseppe","Donnarumma",25,"attaccante",true,"avanzato");
        Giocatore g4 = new Giocatore("LGSRRN96L05F839Z", "Luigi","Sorrentino",27,"difensore",false,"principiante");
        Giocatore g5 = new Giocatore("ANTRCC98A22F839F", "Antonio","Ruocco",29,"attaccante",false,"avanzato");
        Giocatore g6 = new Giocatore("GSPRCC98L03F839X", "Giuseppe","Ruocco",29,"centrocampista",false,"avanzato");
        Giocatore g7 = new Giocatore("ANTBNC95L01F839R", "Antonio","Bianchi",29,"attaccante",true,"avanzato");
        Giocatore g8 = new Giocatore("GLCVCD96C20F839L", "Gianluca","Vicedomini",28,"centrocampista",false,"intermedio");
        Giocatore g9 = new Giocatore("DLRBSR96D05F839Z", "Antonio","Delsorbo",29,"attaccante",false,"intermedio");
        Giocatore g10 = new Giocatore("FRNVCD98A25F839M", "Francesco","Vicedomini",27,"attaccante",false,"intermedio");
        Giocatore g11 = new Giocatore("JCPFRT97E20F839K", "Jacopo","Fortunato",26,"centrocampista",true,"avanzato");

        squadre.get(0).inserisciGiocatore(g1);
        squadre.get(0).inserisciGiocatore(g2);
        squadre.get(1).inserisciGiocatore(g3);
        squadre.get(1).inserisciGiocatore(g4);
        squadre.get(1).inserisciGiocatore(g5);
        squadre.get(1).inserisciGiocatore(g6);
        squadre.get(2).inserisciGiocatore(g7);
        squadre.get(2).inserisciGiocatore(g8);
        squadre.get(2).inserisciGiocatore(g9);
        squadre.get(3).inserisciGiocatore(g10);
        squadre.get(3).inserisciGiocatore(g11);


        //indice scelta squadra
        int indexSquadra = 0;

        //Recupero dei giocatori dal DataManager
        ArrayList<Giocatore> Tesserati = DataManager.getInstance().getTessrati();
        ArrayList<Giocatore> Player = DataManager.getInstance().getPlayer();
        ArrayList<Giocatore> portieri = DataManager.getInstance().getPortieri();

        //primo shuffle
        Collections.shuffle(Tesserati);
        Collections.shuffle(Player);
        Collections.shuffle(portieri);


        //inserimento portieri
        for(Giocatore g : portieri) {
            squadre.get(indexSquadra % Nteam).inserisciGiocatore(g);
            indexSquadra++;
        }

        //defizione dei ruoli
        String[] ruoli = {"difensore", "centrocampista", "attaccante"};

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
        for (String ruolo : ruoli) {
            ArrayList<Giocatore> rankB = new ArrayList<>();
            ArrayList<Giocatore> rankC = new ArrayList<>();
            ArrayList<Giocatore> rankD = new ArrayList<>();

            for (Giocatore g : Player) {

                if (!g.getRuolo().equals(ruolo)) continue;

                int punteggio = g.getPunteggio();
                if (punteggio == 7) {
                    rankB.add(g);
                } else if (punteggio == 4) {
                    rankC.add(g);
                } else if (punteggio == 1) {
                    rankD.add(g);
                }
            }

            Collections.shuffle(rankB);
            Collections.shuffle(rankC);
            Collections.shuffle(rankD);

            ArrayList<Giocatore> ordinati = new ArrayList<>();
            ordinati.addAll(rankB);
            ordinati.addAll(rankC);
            ordinati.addAll(rankD);

            for (Giocatore g : ordinati) {
                ArrayList<Squadra> squadreOrdinate = new ArrayList<>(squadre);
                squadreOrdinate.sort(new Comparator<Squadra>() {
                    @Override
                    public int compare(Squadra s1, Squadra s2) {
                        return Integer.compare(s1.getScore(), s2.getScore());
                    }
                });

                Squadra squadraScelta = squadreOrdinate.get(0);
                squadraScelta.inserisciGiocatore(g); // aggiorna già il punteggio
            }
        }

        boolean bilanciato = false;
        int soglia = 1;

        while (!bilanciato) {
            Squadra squadraMin = Collections.min(squadre, Comparator.comparingInt(Squadra::getNumGiocatori));
            Squadra squadraMax = Collections.max(squadre, Comparator.comparingInt(Squadra::getNumGiocatori));

            int diff = squadraMax.getNumGiocatori() - squadraMin.getNumGiocatori();

            if (diff <= soglia) {
                bilanciato = true; // bilanciamento raggiunto
            } else {
                // Sposta l'ultimo giocatore da squadraMax a squadraMin
                ArrayList<Giocatore> listaMax = squadraMax.getListaGiocatori();
                if (!listaMax.isEmpty()) {
                    Giocatore toMove = listaMax.get(listaMax.size() - 1);
                    squadraMax.rimuoviGiocatore(toMove.getCodiceFiscale());
                    squadraMin.inserisciGiocatore(toMove);
                } else {
                    // Se squadraMax è vuota (improbabile), fermati per evitare ciclo infinito
                    break;
                }
            }
        }





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

//        for(String r : ruoli){
//            for(Giocatore g : Player) {
//                if(g.getRuolo().equals(r)) {
//                    squadre.get(indexSquadra % Nteam).inserisciGiocatore(g);
//                    indexSquadra++;
//                }
//            }
//        }

        DataManager.getInstance().addSquadre(squadre);
    }
}

package com.mycompany.tennis;

import com.mycompany.tennis.controller.*;

public class UI {

    public static void main(String... args){
        JoueurController joueurController = new JoueurController();
        TournoiController tournoiController = new TournoiController();
        ScoreController scoreController = new ScoreController();
        EpreuveController epreuveController = new EpreuveController();
        MatchController matchController = new MatchController();
        joueurController.afficheListeJoueurs();
        //tournoiController.supprimeTournoi();
        //scoreController.supprimerScore();
        //epreuveController.afficheListeEpreuvesParTournoi();
        //matchController.ajouterMatch();
    }
}

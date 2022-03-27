package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.ScoreService;

import java.util.Scanner;

public class ScoreController {

    private ScoreService scoreService;

    public ScoreController(){
        this.scoreService = new ScoreService();
    }

    public void afficherDetailsScore(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du score que vous voulez afficher ?");
        long identifiant = scanner.nextLong();
        ScoreFullDto score= scoreService.getScore(identifiant);
        System.out.println("Le nom du tournoi est " + score.getMatchDto().getEpreuveFullDto().getTournoiDto().getNom());
        System.out.println("L'année de l'épreuve est " + score.getMatchDto().getEpreuveFullDto().getAnnee());
        System.out.println("le type d'épreuve est " + score.getMatchDto().getEpreuveFullDto().getTypeEpreuve());
        System.out.println("Les scores sont : 1er set : " + score.getSet1() + ", 2eme set : " + score.getSet2()
                + (score.getSet3() != null ? ", 3eme set : " + score.getSet3() : "")
                + (score.getSet4() != null ? ", 4eme set : " + score.getSet4() : "")
                + (score.getSet5() != null ? ", 5eme set : " + score.getSet5() : ""));
    }

    public void supprimerScore(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du score à supprimer ?");
        long identifiant = scanner.nextLong();
        scoreService.deleteScore(identifiant);
    }
}

package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.service.EpreuveService;
import com.mycompany.tennis.core.service.MatchService;

import java.util.Scanner;

public class MatchController {

    private MatchService matchService;

    public MatchController(){
        this.matchService = new MatchService();
    }

    public void afficherDeetailsMatch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du match dont vous voulez afficher les informations ?");
        long identifiant = scanner.nextLong();
        MatchDto matchDto = matchService.getMatch(identifiant);
        System.out.println("Il s'agit un match de : " + matchDto.getEpreuveFullDto().getAnnee() + " qui s'est déroulé à " + matchDto.getEpreuveFullDto().getTournoiDto().getNom());
        System.out.println("Le nom du vainqueur est : " + matchDto.getVainqueur().getNom()
                + ", le finaliste est : " +matchDto.getFinaliste().getNom());
        System.out.println("Les scores sont : 1er set : " + matchDto.getScoreFullDto().getSet1() + ", 2eme set : " + matchDto.getScoreFullDto().getSet2()
                + (matchDto.getScoreFullDto().getSet3() != null ? ", 3eme set : " + matchDto.getScoreFullDto().getSet3() : "")
                + (matchDto.getScoreFullDto().getSet4() != null ? ", 4eme set : " + matchDto.getScoreFullDto().getSet4() : "")
                + (matchDto.getScoreFullDto().getSet5() != null ? ", 5eme set : " + matchDto.getScoreFullDto().getSet5() : ""));
    }

    public void tapisVert(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du match que vous voulez modifier ?");
        long identifiant = scanner.nextLong();
        matchService.tapisVert(identifiant);
    }

    public void ajouterMatch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'épreuve ?");
        long epreuveId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est l'identifiant du vainqueur ?");
        long vainqueurId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est l'identifiant du finaliste ?");
        long finalisteId = scanner.nextLong();
        scanner.nextLine();
        MatchDto matchDto = new MatchDto();
        matchDto.setEpreuveFullDto(new EpreuveFullDto());
        matchDto.getEpreuveFullDto().setId(epreuveId);
        matchDto.setFinaliste(new JoueurDto());
        matchDto.getFinaliste().setId(finalisteId);
        matchDto.setVainqueur(new JoueurDto());
        matchDto.getVainqueur().setId(vainqueurId);

        System.out.println("Quel est la valeur du 1er set ?");
        byte set1 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 2er set ?");
        byte set2 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 3er set ?");
        byte set3 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 4er set ?");
        byte set4 = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 5er set ?");
        byte set5 = scanner.nextByte();
        scanner.nextLine();

        ScoreFullDto scoreFullDto = new ScoreFullDto();
        scoreFullDto.setSet1(set1);
        scoreFullDto.setSet2(set2);
        scoreFullDto.setSet3(set3);
        scoreFullDto.setSet4(set4);
        scoreFullDto.setSet5(set5);
        matchDto.setScoreFullDto(scoreFullDto);
        scoreFullDto.setMatchDto(matchDto);

        matchService.createMatch(matchDto);

    }

    public void supprimerMatch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du match à supprimer ?");
        long identifiant = scanner.nextLong();
        matchService.deleteMatch(identifiant);
    }


}

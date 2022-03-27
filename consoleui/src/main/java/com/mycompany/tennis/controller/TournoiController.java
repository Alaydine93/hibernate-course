package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.Scanner;

public class TournoiController {

    private TournoiService tournoiService;

    public TournoiController(){
        this.tournoiService = new TournoiService();
    }

    public void afficherDetailsTournoi(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du tournoi dont vous voulez afficher les informations ?");
        long identifiant = scanner.nextLong();
        TournoiDto tournoi = tournoiService.getTournoiById(identifiant);
        System.out.println(tournoi.getNom() + " " + tournoi.getCode());

    }

    public void creerTournoi() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le nom du tournoi à créer ?");
        String nom = scanner.nextLine();
        System.out.println("Quel est le code du tournoi à créer ?");
        String code = scanner.nextLine();
        TournoiDto tournoi = new TournoiDto();
        tournoi.setNom(nom);
        tournoi.setCode(code);
        tournoiService.createTournoi(tournoi);
        System.out.println("Le tournoi a été créé, son identifiant est " + tournoi.getId());
    }

    public void supprimeTournoi(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du tournoi que vous voulez supprimer ?");
        long identifiant = scanner.nextLong();
        tournoiService.deleteTournoi(identifiant);
    }
}

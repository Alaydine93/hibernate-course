package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.EpreuveService;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.Scanner;

public class EpreuveController {

    private EpreuveService epreuveService;

    public EpreuveController(){
        this.epreuveService = new EpreuveService();
    }

    public void afficherDetailsEpreuve(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez afficher les informations ?");
        long identifiant = scanner.nextLong();
        EpreuveFullDto epreuveDto = epreuveService.getEpreuveDetaillee(identifiant);
        System.out.println("Le nom du tournoi est : " + epreuveDto.getTournoiDto().getNom());
        System.out.println("Les participants de ce tournoi sont ");
        for(JoueurDto participant : epreuveDto.getParticipants()){
            System.out.println(participant.getPrenom() + " " + participant.getNom());
        }
    }

    public void afficherRolandGarros(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez afficher les informations ?");
        long identifiant = scanner.nextLong();
        EpreuveLightDto epreuve = epreuveService.getEpreuveByIdSansTournoi(identifiant);
    }

    public void afficheListeEpreuvesParTournoi(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le code du tournoi dont vous voulez afficher les épreuves ?");
        String codeTournoi = scanner.nextLine();
        for(EpreuveFullDto dto : epreuveService.getListeEpreuves(codeTournoi)){
            System.out.println(dto.getAnnee() + " " + dto.getTypeEpreuve() + " " + dto.getTournoiDto().getNom());
        }
    }
}

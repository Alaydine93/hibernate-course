package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;

import java.util.Scanner;

public class JoueurController {

    private JoueurService joueurService;

    public JoueurController() {
        this.joueurService = new JoueurService();
    }

    public void afficheDetailsJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur dont vous voulez afficher les informations ?");
        long identifiant = scanner.nextLong();
        Joueur joueur = joueurService.getJoueur(identifiant);
        System.out.println("Le joueur sélectionné s'appelle : " + joueur.getPrenom() + " " + joueur.getNom());
    }

    public void creerJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le nom du joueur ?");
        String nom = scanner.nextLine();
        System.out.println("Quel est le prénom du joueur ?");
        String prenom = scanner.nextLine();
        System.out.println("Quel est le sexe du joueur ?");
        char sexe = scanner.nextLine().charAt(0);
        Joueur joueur = new Joueur();
        joueur.setNom(nom);
        joueur.setPrenom(prenom);
        joueur.setSexe(sexe);
        joueurService.createJoueur(joueur);
        System.out.println("Le joueur a été créé, son identifiant est " + joueur.getId());
    }

    public void renommeJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur que vous voulez renommer ?");
        long identifiant = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est le nouveau nom ?");
        String nouveauNom = scanner.nextLine();
        joueurService.renomme(identifiant, nouveauNom);
    }

    public void changeSexe() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur que vous voulez changer de sexe ?");
        long identifiant = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est le nouveau sexe ?");
        Character nouveauSexe = scanner.nextLine().charAt(0);
        joueurService.changeSexe(identifiant, nouveauSexe);
    }

    public void supprimeJoueur(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur que vous voulez supprimer ?");
        long identifiant = scanner.nextLong();
        joueurService.deleteJoueur(identifiant);
    }

    public void afficheListeJoueurs(){
        //Scanner scanner = new Scanner(System.in);
       // System.out.println("Quel est le sexe des joueurs à afficher (H ou F) ?");
        //char sexe = scanner.nextLine().charAt(0);
        for(JoueurDto joueurDto : joueurService.getListeJoueurs('F')){
            System.out.println(joueurDto.getPrenom() + " " + joueurDto.getPrenom());
        }
        for(JoueurDto joueurDto : joueurService.getListeJoueurs('H')){
            System.out.println(joueurDto.getPrenom() + " " + joueurDto.getPrenom());
        }
    }
}

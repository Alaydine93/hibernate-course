package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur) {

        //Permet de récupérer une nouvelle session (espace mémoire réservé à Hibernate)
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(joueur);
        //Synchronise l'état de la session avec la base de donnée
        System.out.println("Joueur créé");
    }

    public Joueur getById(Long id) {
        Joueur joueur = null;
        EntityManager em = new EntityManagerHolder().getCurrentEntityManager();
        joueur = em.find(Joueur.class, id);
        System.out.println("Joueur lu");
        return joueur;
    }

    public void delete(Long id) {
        Joueur joueur = getById(id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(joueur);
        System.out.println("Joueur supprimé");

    }


    public List<Joueur> list(char sexe) {
        EntityManager em = new EntityManagerHolder().getCurrentEntityManager();
        TypedQuery<Joueur> query = em.createNamedQuery("given sexe", Joueur.class);
        query.setParameter("sexe", sexe);
        List<Joueur> joueurs = query.getResultList();
        System.out.println("Joueurs lus");
        return joueurs;
    }
}

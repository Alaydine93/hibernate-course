package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    public void createJoueur(Joueur joueur) {
        Session session = null;
        Transaction tx = null;
        try {
            //Permet de récupérer une nouvelle session (espace mémoire réservé à Hibernate)
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.create(joueur);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void renomme(Long id, String nouveauNom) {
        Joueur joueur = getJoueur(id);
        Session session = null;
        Transaction tx = null;
        try {
            //Permet de récupérer une nouvelle session (espace mémoire réservé à Hibernate)
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueur.setNom(nouveauNom);
            Joueur joueur2 = (Joueur) session.merge(joueur);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void changeSexe(Long id, Character nouveauSexe) {
        Joueur joueur = getJoueur(id);
        Session session = null;
        Transaction tx = null;
        try {
            //Permet de récupérer une nouvelle session (espace mémoire réservé à Hibernate)
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueur.setSexe(nouveauSexe);
            Joueur joueur2 = (Joueur) session.merge(joueur);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Joueur getJoueur(Long id) {
        Session session = null;
        Transaction tx = null;
        Joueur joueur = null;
        try {
            //Permet de récupérer une nouvelle session (espace mémoire réservé à Hibernate)
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur = joueurRepository.getById(id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return joueur;
    }

    public void deleteJoueur(Long id){
        Session session = null;
        Transaction tx = null;
        try {
            //Permet de récupérer une nouvelle session (espace mémoire réservé à Hibernate)
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.delete(id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Transactional
    public List<JoueurDto> getListeJoueurs(char sexe){
        EntityManager em = null;
        EntityTransaction tx = null;
        List<JoueurDto> joueursDto = new ArrayList<>();
        try {
            em = new EntityManagerHolder().getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            List<Joueur> joueurs = joueurRepository.list(sexe);
            for(Joueur joueur : joueurs){
                final JoueurDto joueurDto = new JoueurDto();
                joueurDto.setId(joueur.getId());
                joueurDto.setPrenom(joueur.getPrenom());
                joueurDto.setNom(joueur.getNom());
                joueurDto.setSexe(joueur.getSexe());
                joueursDto.add(joueurDto);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return joueursDto;
    }
}

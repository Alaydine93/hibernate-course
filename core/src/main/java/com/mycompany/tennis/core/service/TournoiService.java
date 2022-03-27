package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TournoiService {

    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public TournoiDto getTournoiById(Long id) {
        //Session session = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        Tournoi tournoi = null;
        TournoiDto tournoiDto = null;
        try {
            //session = HibernateUtil.getSessionFactory().getCurrentSession();
            em = new EntityManagerHolder().getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tournoi = tournoiRepository.getTournoiById(id);
            tournoiDto = new TournoiDto();
            tournoiDto.setId(tournoi.getId());
            tournoiDto.setCode(tournoi.getCode());
            tournoiDto.setNom(tournoi.getNom());
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
        return tournoiDto;
    }

    public void createTournoi(TournoiDto tournoiDto) {
        //Session session = null;
        EntityTransaction tx = null;
        EntityManager em = null;
        try {
            //Permet de récupérer une nouvelle session (espace mémoire réservé à Hibernate)
            //session = HibernateUtil.getSessionFactory().getCurrentSession();
            em = new EntityManagerHolder().getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Tournoi tournoi = new Tournoi();
            tournoi.setId(tournoiDto.getId());
            tournoi.setCode(tournoiDto.getCode());
            tournoi.setNom(tournoiDto.getNom());
            tournoiRepository.createTournoi(tournoi);
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
    }

    public void deleteTournoi(Long id) {
        EntityTransaction tx = null;
        EntityManager em = null;
        try {
            em = new EntityManagerHolder().getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tournoiRepository.deleteTournoi(id);
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
    }
}

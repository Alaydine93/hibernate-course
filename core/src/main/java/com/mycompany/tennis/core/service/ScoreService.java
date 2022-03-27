package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    private ScoreRepositoryImpl scoreRepository;

    public ScoreService(){
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public ScoreFullDto getScore(Long id){
        Session session = null;
        Transaction tx = null;
        ScoreFullDto scoreFullDto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Score score = scoreRepository.getById(id);

            scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(score.getId());
            scoreFullDto.setSet1(score.getSet1());
            scoreFullDto.setSet2(score.getSet2());
            scoreFullDto.setSet3(score.getSet3());
            scoreFullDto.setSet4(score.getSet4());
            scoreFullDto.setSet5(score.getSet5());

            MatchDto matchDto = new MatchDto();
            matchDto.setId(score.getMatch().getId());

            JoueurDto vainqueur = new JoueurDto();
            vainqueur.setId(score.getMatch().getVainqueur().getId());
            vainqueur.setNom(score.getMatch().getVainqueur().getNom());
            vainqueur.setPrenom(score.getMatch().getVainqueur().getPrenom());
            vainqueur.setSexe(score.getMatch().getVainqueur().getSexe());
            matchDto.setVainqueur(vainqueur);

            JoueurDto finaliste = new JoueurDto();
            finaliste.setId(score.getMatch().getFinaliste().getId());
            finaliste.setNom(score.getMatch().getFinaliste().getNom());
            finaliste.setPrenom(score.getMatch().getFinaliste().getPrenom());
            finaliste.setSexe(score.getMatch().getFinaliste().getSexe());
            matchDto.setFinaliste(finaliste);

            EpreuveFullDto epreuveFullDto = new EpreuveFullDto();
            epreuveFullDto.setId(score.getMatch().getEpreuve().getId());
            epreuveFullDto.setAnnee(score.getMatch().getEpreuve().getAnnee());
            epreuveFullDto.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
            tournoiDto.setCode(score.getMatch().getEpreuve().getTournoi().getCode());
            tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());
            epreuveFullDto.setTournoiDto(tournoiDto);
            matchDto.setEpreuveFullDto(epreuveFullDto);

            scoreFullDto.setMatchDto(matchDto);
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
        return  scoreFullDto;
    }

    public void deleteScore(Long id) {
        Session session = null;
        Transaction tx = null;
        Score score = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            scoreRepository.deleteScore(id);
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
}

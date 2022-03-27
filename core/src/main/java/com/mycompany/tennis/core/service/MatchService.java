package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
    private EpreuveRepositoryImpl epreuveRepository;
    private JoueurRepositoryImpl joueurRepository;

    public MatchService() {
        this.scoreRepository = new ScoreRepositoryImpl();
        this.matchRepository = new MatchRepositoryImpl();
        this.epreuveRepository = new EpreuveRepositoryImpl();
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    public void enregistrerNouveauMatch(Match match) {
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
    }

    public void deleteMatch(Long id) {
        Session session = null;
        Transaction tx = null;
        Match match = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            matchRepository.deleteMatch(id);
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

    public void createMatch(MatchDto matchDto) {
        Session session = null;
        Transaction tx = null;
        Match match = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match = new Match();
            match.setEpreuve(epreuveRepository.getEpreuveById(matchDto.getEpreuveFullDto().getId()));
            match.setVainqueur(joueurRepository.getById(matchDto.getVainqueur().getId()));
            match.setFinaliste(joueurRepository.getById(matchDto.getFinaliste().getId()));
            Score score = new Score();
            score.setMatch(match);
            match.setScore(score);
            score.setSet1(matchDto.getScoreFullDto().getSet1());
            score.setSet2(matchDto.getScoreFullDto().getSet2());
            score.setSet3(matchDto.getScoreFullDto().getSet3());
            score.setSet4(matchDto.getScoreFullDto().getSet4());
            score.setSet5(matchDto.getScoreFullDto().getSet5());

            matchRepository.create(match);
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

    public void tapisVert(Long id) {
        Session session = null;
        Transaction tx = null;
        Match match;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match = matchRepository.getById(id);

            Joueur ancienVainqueur = match.getVainqueur();
            match.setVainqueur(match.getFinaliste());
            match.setFinaliste(ancienVainqueur);

            match.getScore().setSet1((byte) 0);
            match.getScore().setSet2((byte) 0);
            match.getScore().setSet3((byte) 0);
            match.getScore().setSet4((byte) 0);
            match.getScore().setSet5((byte) 0);

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

    public MatchDto getMatch(Long id) {
        Session session = null;
        Transaction tx = null;
        Match match;
        MatchDto matchDto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match = matchRepository.getById(id);

            matchDto = new MatchDto();
            matchDto.setId(match.getId());

            JoueurDto vainqueur = new JoueurDto();
            vainqueur.setId(match.getVainqueur().getId());
            vainqueur.setNom(match.getVainqueur().getNom());
            vainqueur.setPrenom(match.getVainqueur().getPrenom());
            vainqueur.setSexe(match.getVainqueur().getSexe());
            matchDto.setVainqueur(vainqueur);

            JoueurDto finaliste = new JoueurDto();
            finaliste.setId(match.getFinaliste().getId());
            finaliste.setNom(match.getFinaliste().getNom());
            finaliste.setPrenom(match.getFinaliste().getPrenom());
            finaliste.setSexe(match.getFinaliste().getSexe());
            matchDto.setFinaliste(finaliste);

            EpreuveFullDto epreuveFullDto = new EpreuveFullDto();
            epreuveFullDto.setId(match.getEpreuve().getId());
            epreuveFullDto.setAnnee(match.getEpreuve().getAnnee());
            epreuveFullDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            epreuveFullDto.setTournoiDto(tournoiDto);

            ScoreFullDto scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(match.getScore().getId());
            scoreFullDto.setSet1(match.getScore().getSet1());
            scoreFullDto.setSet2(match.getScore().getSet2());
            scoreFullDto.setSet3(match.getScore().getSet3());
            scoreFullDto.setSet4(match.getScore().getSet4());
            scoreFullDto.setSet5(match.getScore().getSet5());

            matchDto.setScoreFullDto(scoreFullDto);
            scoreFullDto.setMatchDto(matchDto);

            matchDto.setEpreuveFullDto(epreuveFullDto);
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
        return matchDto;
    }
}

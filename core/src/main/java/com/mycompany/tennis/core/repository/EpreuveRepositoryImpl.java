package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Epreuve;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EpreuveRepositoryImpl {

    public Epreuve getEpreuveById(Long id) {
        EntityManager em =  new EntityManagerHolder().getCurrentEntityManager();
        Epreuve epreuve = em.find(Epreuve.class, id);
        System.out.println("Epreuve lu");
        return epreuve;
    }

    public List<Epreuve> list(String codeTournoi) {
        EntityManager em =  new EntityManagerHolder().getCurrentEntityManager();
        TypedQuery<Epreuve> query = em.createQuery("SELECT e FROM Epreuve e join fetch e.tournoi WHERE e.tournoi.code=?1", Epreuve.class);
        query.setParameter(1, codeTournoi);
        List<Epreuve> epreuves = query.getResultList();
        System.out.println("Joueurs lus");
        return epreuves;
    }

}

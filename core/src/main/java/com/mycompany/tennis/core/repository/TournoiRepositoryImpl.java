package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void createTournoi(Tournoi tournoi) {
        EntityManager em =  new EntityManagerHolder().getCurrentEntityManager();
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        em.persist(tournoi);
        System.out.println("Tournoi ajouté");
    }

    public void updateTournoi(Tournoi tournoi) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE TOURNOI SET NOM = ?, CODE = ? WHERE ID = ?");

            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());
            preparedStatement.setLong(3, tournoi.getId());

            preparedStatement.executeUpdate();

            System.out.println("Tournoi modifié");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteTournoi(Long id) {
        EntityManager em =  new EntityManagerHolder().getCurrentEntityManager();
        Tournoi tournoi = em.find(Tournoi.class, id);
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        em.remove(tournoi);
        System.out.println("Tournoi supprimé");
    }

    public Tournoi getTournoiById(Long id) {
        EntityManager em =  new EntityManagerHolder().getCurrentEntityManager();
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Tournoi tournoi = em.find(Tournoi.class, id);
        System.out.println("Tournoi lu");
        return tournoi;
    }

    public List<Tournoi> getAllTournois() {
        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID, NOM, CODE FROM tournoi");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Tournoi tournoi = new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
                tournois.add(tournoi);
            }

            System.out.println("Tournois lus");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tournois;
    }
}

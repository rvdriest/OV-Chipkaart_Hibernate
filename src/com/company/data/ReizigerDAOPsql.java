package com.company.data;

import com.company.domein.Reiziger;
import com.company.domein.ReizigerDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private final SessionFactory sessionFactory;

    public ReizigerDAOPsql(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(reiziger);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(reiziger);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(reiziger);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Reiziger findById(int id) {
        Session session = sessionFactory.openSession();
        Reiziger reiziger = session.get(Reiziger.class, id);
        session.close();
        return reiziger;
    }
    
    @Override
    public List<Reiziger> findByGbDatum(String datum) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT r FROM Reiziger r WHERE r.geboorteDatum = '" + datum + "'");
        List<Reiziger> reizigers = query.getResultList();
        session.close();
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() {
        Session session = sessionFactory.openSession();
        List<Reiziger> reizigers = (List<Reiziger>) session.createQuery("from Reiziger").list();
        session.close();
        return reizigers;
    }
}

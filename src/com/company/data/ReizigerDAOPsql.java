package com.company.data;

import com.company.domein.Reiziger;
import com.company.domein.ReizigerDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private final Session session;

    public ReizigerDAOPsql(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        boolean isSuccess = true;
        try {
            session.beginTransaction();
            session.saveOrUpdate(reiziger);
            session.getTransaction().commit();
        }catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.getTransaction().rollback();
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        boolean isSuccess = true;
        try {
            session.getTransaction().begin();
            session.update(reiziger);
            session.getTransaction().commit();
        }catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.getTransaction().rollback();
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        boolean isSuccess = true;
        try {
            session.beginTransaction();
            session.delete(reiziger);
            session.getTransaction().commit();
        }catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.getTransaction().rollback();
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public Reiziger findById(int id) {
        Reiziger reiziger = session.get(Reiziger.class, id);
        return reiziger;
    }
    
    @Override
    public List<Reiziger> findByGbDatum(String datum) {
        Query query = session.createQuery("SELECT r FROM Reiziger r WHERE r.geboorteDatum = '" + datum + "'");
        List<Reiziger> reizigers = query.getResultList();
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() {
        List<Reiziger> reizigers = (List<Reiziger>) session.createQuery("from Reiziger").list();
        return reizigers;
    }
}

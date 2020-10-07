package com.company.data;

import com.company.domein.Adres;
import com.company.domein.AdresDAO;
import com.company.domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    private final Session session;

    public AdresDAOPsql(final Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Adres adres) {
        boolean isSucces = true;
        try {
            session.beginTransaction();
            session.saveOrUpdate(adres);
            session.getTransaction().commit();
        }catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.getTransaction().rollback();
            isSucces = false;
        }
        return isSucces;
    }

    @Override
    public boolean update(Adres adres) {
        boolean isSucces = true;
        try {
            session.beginTransaction();
            session.update(adres);
            session.getTransaction().commit();
        }catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.getTransaction().rollback();
            isSucces = false;
        }
        return isSucces;
    }

    @Override
    public boolean delete(Adres adres) {
        boolean isSucces = true;
        try {
            session.beginTransaction();
            session.delete(adres);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            session.getTransaction().rollback();
            isSucces = false;
        }
        return isSucces;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        List<Adres> adressen = session.createQuery("from Adres adres where adres.reiziger.id = " + reiziger.getId()).list();
        Adres adres = null;
        if(adressen.size() > 0) adres = adressen.get(0);
        return adres;
    }

    @Override
    public List<Adres> findAll() {
        List<Adres> adressen = (List<Adres>) session.createQuery("from Adres ").list();
        return adressen;
    }
}

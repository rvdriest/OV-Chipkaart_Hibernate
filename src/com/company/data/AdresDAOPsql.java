package com.company.data;

import com.company.domein.Adres;
import com.company.domein.AdresDAO;
import com.company.domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    private final SessionFactory sessionFactory;

    public AdresDAOPsql(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Adres adres) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(adres);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Adres adres) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(adres);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Adres adres) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(adres);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        List<Adres> adressen = session.createQuery("from Adres adres where adres.reiziger.id = " + reiziger.getId()).list();
        Adres adres = null;
        if(adressen.size() > 0) adres = adressen.get(0);
        session.close();
        return adres;
    }

    @Override
    public List<Adres> findAll() {
        Session session = sessionFactory.openSession();
        List<Adres> adressen = (List<Adres>) session.createQuery("from Adres ").list();
        session.close();
        return adressen;
    }
}

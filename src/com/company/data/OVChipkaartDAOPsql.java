package com.company.data;

import com.company.domein.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private final SessionFactory sessionFactory;

    public OVChipkaartDAOPsql(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(OVChipkaart ovChipkaart) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(ovChipkaart);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(OVChipkaart ovChipkaart) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(ovChipkaart);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(OVChipkaart ovChipkaart) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(ovChipkaart);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        Session session = sessionFactory.openSession();
        List<OVChipkaart> ovChipkaarten = (List<OVChipkaart>) session.createQuery("from OVChipkaart ov where ov.reiziger.id = " + reiziger.getId()).list();
        session.close();
        return ovChipkaarten;
    }

    @Override
    public List<OVChipkaart> findAll() {
        Session session = sessionFactory.openSession();
        List<OVChipkaart> ovChipkaarten = (List<OVChipkaart>) session.createQuery("from OVChipkaart").list();
        session.close();
        return ovChipkaarten;
    }
}

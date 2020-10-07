package com.company.data;

import com.company.domein.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private final Session session;

    public OVChipkaartDAOPsql(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        boolean isSuccess = true;
        try {
            session.beginTransaction();
            session.saveOrUpdate(ovChipkaart);
            session.getTransaction().commit();
        }catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.getTransaction().rollback();
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        boolean isSuccess = true;
        try {
            session.beginTransaction();
            session.update(ovChipkaart);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            session.getTransaction().rollback();
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        boolean isSuccess = true;
        try {
            session.beginTransaction();
            session.delete(ovChipkaart);
            session.getTransaction().commit();
        }catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.getTransaction().rollback();
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        List<OVChipkaart> ovChipkaarten = (List<OVChipkaart>) session.createQuery("from OVChipkaart ov where ov.reiziger.id = " + reiziger.getId()).list();
        return ovChipkaarten;
    }

    @Override
    public List<OVChipkaart> findAll() {
        List<OVChipkaart> ovChipkaarten = (List<OVChipkaart>) session.createQuery("from OVChipkaart").list();
        return ovChipkaarten;
    }
}

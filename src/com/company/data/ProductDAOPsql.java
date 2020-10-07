package com.company.data;

import com.company.domein.OVChipkaart;
import com.company.domein.Product;
import com.company.domein.ProductDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAOPsql implements ProductDAO {
    private final Session session;

    public ProductDAOPsql(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Product product) {
        boolean isSuccess = true;
        try {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.getTransaction().rollback();
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public boolean update(Product product) {
        boolean isSuccess = true;
        try {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        }catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.getTransaction().rollback();
            isSuccess = false;
        }
        return isSuccess;

    }

    @Override
    public boolean delete(Product product) {
        boolean isSuccess = true;
        try {
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        }catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.getTransaction().rollback();
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        List<Product> producten = (List<Product>) session.createQuery("SELECT p from Product p JOIN p.ovChipkaarten o WHERE o.kaartnummer = " + ovChipkaart.getKaartnummer()).list();
        return producten;
    }

    @Override
    public List<Product> findAll() {
        List<Product> producten = (List<Product>) session.createQuery("from Product").list();
        return producten;
    }
}

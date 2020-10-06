package com.company.data;

import com.company.domein.OVChipkaart;
import com.company.domein.Product;
import com.company.domein.ProductDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAOPsql implements ProductDAO {
    private final SessionFactory sessionFactory;

    public ProductDAOPsql(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        Session session = sessionFactory.openSession();
        List<Product> producten = (List<Product>) session.createQuery("SELECT p from Product p JOIN p.ovChipkaarten o WHERE o.kaartnummer = " + ovChipkaart.getKaartnummer()).list();
        session.close();
        return producten;
    }

    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        List<Product> producten = (List<Product>) session.createQuery("from Product").list();
        session.close();
        return producten;
    }
}

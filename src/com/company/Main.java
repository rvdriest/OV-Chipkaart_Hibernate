package com.company;

import com.company.data.AdresDAOPsql;
import com.company.data.OVChipkaartDAOPsql;
import com.company.data.ProductDAOPsql;
import com.company.data.ReizigerDAOPsql;
import com.company.domein.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
//        testFetchAll();
        testDAOHibernate();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static void testDAOHibernate() {
        Session session = getSession();
        testReizigerDAO(session);
        testAdresDAO(session);
        testOVChipkaartDAO(session);
        testProductDAO(session);
        reset(session);
        session.close();
    }

    private static void reset(Session session) {
        ReizigerDAO reizigerDAO = new ReizigerDAOPsql(session);
        reizigerDAO.delete(reizigerDAO.findById(7));
    }

    private static void testReizigerDAO(Session session) {
        ReizigerDAO reizigerDAO = new ReizigerDAOPsql(session);
        AdresDAO adresDAO = new AdresDAOPsql(session);

        System.out.println("[Test] findAll()");
        for(Reiziger reiziger : reizigerDAO.findAll()) {
            System.out.println(reiziger);
        }

        System.out.println("\n[Test] findById(2)");
        Reiziger reiziger = reizigerDAO.findById(2);
        System.out.println(reiziger);

        System.out.println("\n[Test] findByGbDatum('2002-12-03')");
        for(Reiziger r : reizigerDAO.findByGbDatum("2002-12-03")) {
            System.out.println(r);
        }

        System.out.println("\n[Test] save()");
        Reiziger r = new Reiziger(7, "R", "van", "Driest", Date.valueOf("2000-12-24"), adresDAO.findAll().get(0));
        reizigerDAO.save(r);
        System.out.println(reizigerDAO.findById(7));

        System.out.println("\n[Test] update()");
        r.setVoorletters("M");
        reizigerDAO.update(r);
        System.out.println(reizigerDAO.findById(7));

        System.out.println("\n[Test] delete()");
        reizigerDAO.delete(reizigerDAO.findById(7));
        reizigerDAO.findAll().forEach(reiziger1 -> System.out.println(reiziger1));
    }
    private static void testAdresDAO(Session session) {
        AdresDAO adresDAO = new AdresDAOPsql(session);
        ReizigerDAO reizigerDAO = new ReizigerDAOPsql(session);

        System.out.println("[Test] findAll()");
        for(Adres adres : adresDAO.findAll()) {
            System.out.println(adres);
        }

        System.out.println("\n[Test] findByReiziger(3)");
        Reiziger reiziger = reizigerDAO.findById(3);
        Adres adres = adresDAO.findByReiziger(reiziger);
        System.out.println(adres);

        System.out.println("\n[Test] save()");
        Reiziger r = new Reiziger(7, "R", "van", "Driest", Date.valueOf("2000-12-24"), adresDAO.findAll().get(0));
        reizigerDAO.save(r);
        adres = new Adres(10, "3731XC", "67", "Aeolusweg", "De Bilt", reizigerDAO.findById(7));
        adresDAO.save(adres);
        System.out.println(adresDAO.findByReiziger(r));

        System.out.println("\n[Test] update()");
        adres.setHuisnummer("51");
        adresDAO.update(adres);
        System.out.println(adresDAO.findByReiziger(r));

        System.out.println("\n[Test] delete()");
        adresDAO.delete(adres);
        System.out.println(adresDAO.findByReiziger(r));
    }
    private static void testOVChipkaartDAO(Session session) {
        OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAOPsql(session);
        ReizigerDAO reizigerDAO = new ReizigerDAOPsql(session);

        System.out.println("[Test] findAll()");
        ovChipkaartDAO.findAll().forEach(ov -> System.out.println(ov));

        System.out.println("\n[Test] findByReiziger()");
        System.out.println(ovChipkaartDAO.findByReiziger(reizigerDAO.findById(2)));

        System.out.println("\n[Test] save()");
        OVChipkaart ovChipkaart = new OVChipkaart(90, Date.valueOf("2020-12-09"), 1, 20.0, reizigerDAO.findById(7));
        ovChipkaartDAO.save(ovChipkaart);
        System.out.println(ovChipkaartDAO.findByReiziger(reizigerDAO.findById(7)));

        System.out.println("\n[Test] update()");
        ovChipkaart.setSaldo(18.0);
        ovChipkaartDAO.update(ovChipkaart);
        System.out.println(ovChipkaartDAO.findByReiziger(reizigerDAO.findById(7)));

        System.out.println("\n[Test] delete()");
        ovChipkaartDAO.delete(ovChipkaart);
        System.out.println(ovChipkaartDAO.findByReiziger(reizigerDAO.findById(7)));
        System.out.println(reizigerDAO.findById(7));
    }
    private static void testProductDAO(Session session) {
        ProductDAO productDAO = new ProductDAOPsql(session);
        OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAOPsql(session);

        System.out.println("[Test] findAll()");
        productDAO.findAll().forEach(p -> System.out.println(p));

        System.out.println("\n[Test] findByOVChipkaart()");
        OVChipkaart ovChipkaart = ovChipkaartDAO.findAll().get(0);
        System.out.println(productDAO.findByOVChipkaart(ovChipkaart));

        System.out.println("\n[Test] save()");
        Product product = new Product(20, "Koningsdag korting", "Korting op Koningsdag", 15.00);
        productDAO.save(product);
        productDAO.findAll().forEach(p -> System.out.println(p));

        System.out.println("\n[Test] update()");
        product.setPrijs(20.00);
        productDAO.update(product);
        productDAO.findAll().forEach(p -> System.out.println(p));

        System.out.println("\n[Test] delete()");
        productDAO.delete(product);
        productDAO.findAll().forEach(p -> System.out.println(p));
    }
}
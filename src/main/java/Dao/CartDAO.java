package Dao;
import Entities.*;
import Interfaces.DAO;
import Utility.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CartDAO implements DAO<Cart> {

    @Override
    public void save(Cart obj) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Cart obj) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(obj);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Cart obj) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Cart findById(int id) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Cart address = null;
        try {
            address = session.get(Cart.class, id);
        } finally {
            session.close();
        }
        return address;
    }

    public List<Cart> findAll() {
        Session session = HibernateFactory.getSessionFactory().openSession();
        List<Cart> cartList = null;
        try {
            cartList = (List<Cart>) session.createQuery("FROM Cart").list();
        } finally {
            session.close();
        }
        return cartList;
    }
}
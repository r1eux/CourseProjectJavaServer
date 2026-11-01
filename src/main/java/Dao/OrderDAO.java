package Dao;
import Entities.*;
import Interfaces.DAO;
import Utility.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDAO implements DAO {
    @Override
    public void save(Object obj) {
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
    public void update(Object obj) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(obj);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Object obj) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Object findById(int id) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Order order = null;
        try {
            order = session.get(Order.class, id);
        } finally {
            session.close();
        }
        return order;
    }

    public List<Object> findAll() {
        Session session = HibernateFactory.getSessionFactory().openSession();
        List<Object> orderList = null;
        try {
            orderList = (List<Object>) session.createQuery("FROM Order").list();
        } finally {
            session.close();
        }
        return orderList;
    }
}

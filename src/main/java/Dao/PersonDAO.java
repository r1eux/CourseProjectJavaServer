package Dao;
import Entities.*;
import Interfaces.DAO;
import Utility.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
public class PersonDAO implements DAO<Buyer> {

    @Override
    public void save(Buyer obj) {
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
    public void update(Buyer obj) {
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
    public void delete(Buyer obj) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Buyer findById(int id) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Buyer person = null;
        try {
            person = session.get(Buyer.class, id);
        } finally {
            session.close();
        }
        return person;
    }


    public List<Buyer> findAll() {
        Session session = HibernateFactory.getSessionFactory().openSession();
        List<Buyer> personList = null;
        try {
            personList = (List<Buyer>) session.createQuery("FROM Buyer").list();
        } finally {
            session.close();
        }
        return personList;
    }
}

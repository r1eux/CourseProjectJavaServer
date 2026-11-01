package Dao;
import Entities.Role;
import Interfaces.DAO;
import Utility.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
public class RoleDAO implements DAO<Role> {

    @Override
    public void save(Role obj) {
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
    public void update(Role obj) {
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
    public void delete(Role obj) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Role findById(int id) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Role role = null;
        try {
            role = session.get(Role.class, id);
        } finally {
            session.close();
        }
        return role;
    }

    public List<Role> findAll() {
        Session session = HibernateFactory.getSessionFactory().openSession();
        List<Role> roleList = null;
        try {
            roleList = (List<Role>) session.createQuery("FROM Role").list();
        } finally {
            session.close();
        }
        return roleList;
    }
}
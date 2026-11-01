package Dao;

import Entities.Review;
import Interfaces.DAO;
import Utility.HibernateFactory;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReviewDAO implements DAO {

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
        Review review = null;
        try {
            review = session.get(Review.class, id);
        } finally {
            session.close();
        }
        return review;
    }

    public List<Review> findAll() {
        Session session = HibernateFactory.getSessionFactory().openSession();
        List<Review> reviewList = null;
        try {
            reviewList = session.createQuery("FROM Review", Review.class).list();
        } finally {
            session.close();
        }
        return reviewList;
    }
    public List<Review> findByProdId(int prodId) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        List<Review> reviewList = null;
        try {
            reviewList = session.createQuery("FROM Review", Review.class).list();

            // Используем обычный цикл для безопасного удаления
            for (int i = 0; i < reviewList.size(); i++) {
                Review review = reviewList.get(i);
                if (review.getProduct().getProductId() != prodId) {
                    System.out.println("REVIEW LIST ELEMENT: " + new Gson().toJson(review));
                    reviewList.remove(i);
                    i--;  // Сдвигаем индекс, чтобы не пропустить следующий элемент
                }
            }
        } finally {
            session.close();
        }
        System.out.println("FULL LIST !!!!!!!!!!!!!!!!!!!!!!" + new Gson().toJson(reviewList));
        return reviewList;
    }

}

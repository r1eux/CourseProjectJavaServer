package Dao;
import Entities.Product;
import Interfaces.DAO;
import Utility.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAO<Product> {

    @Override
    public void save(Product obj) {
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
    public void update(Product obj) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(obj);
            session.flush();
            session.clear();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Product obj) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Product findById(int id) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Product product = null;
        try {
            product = session.get(Product.class, id);
        } finally {
            session.close();
        }
        return product;
    }
    public List<Product> findByAnything(String data){

        Session session = HibernateFactory.getSessionFactory().openSession();
        String[]spread=data.split(",");
        List<Product>userList=findAll();
        List<Product>userListBack=new ArrayList<Product>();
        Product user=null;
        switch(spread[0]){
            case "name":{
                for(Product user1:userList){
                    if(user1.getProdName().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }break;
            } case "price":{
                for(Product user1:userList){
                    if(user1.getPrice()==Double.parseDouble(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }break;
            } case "color":{
                for(Product user1:userList){
                    if(user1.getColor().getColor().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);
                    }
                }break;
            }case "producer":{
                for(Product user1:userList){
                    if(user1.getProducer().getProducer().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }break;
            }case "url":{
                for(Product user1:userList){
                    if(user1.getImage().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }break;
            }
            case "id":{
                for(Product user1:userList){
                    if(user1.getProductId()==Integer.parseInt(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }
                break;
            }case "category":{
                for(Product user1:userList){
                    if(user1.getCategory().getCategoryName().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }
                break;
            }case "warranty":{
                for(Product user1:userList){
                    if(user1.getWarranty().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }
                break;
            }case "country":{
                for(Product user1:userList){
                    if(user1.getProducer().getProducer_country().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }
                break;
            }



        }

        return userListBack;
    }
    @Override
    public List<Product> findAll() {
        Session session = HibernateFactory.getSessionFactory().openSession();
        List<Product> productList = null;
        try {
            productList = (List<Product>) session.createQuery("FROM Product").list();
        } finally {
            session.close();
        }
        return productList;
    }
}
package Dao;

import Interfaces.DAO;
import Entities.User;
import Utility.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
public class UserDAO implements DAO<User> {

    @Override
    public void save(User obj) {
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
    public void update(User obj) {
        // Открываем сессию через try-with-resources для автоматического закрытия
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction(); // Начинаем транзакцию

            try {
                session.merge(obj); // Используем merge для объединения состояния detached объекта
                transaction.commit(); // Завершаем транзакцию
            } catch (Exception e) {
                transaction.rollback(); // Откатываем транзакцию при ошибке
                throw new RuntimeException("Ошибка при обновлении объекта: " + obj, e);
            }
        }
    }

    @Override
    public void delete(User obj) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public User findById(int id) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        User user = null;
        try {
            user = session.get(User.class, id);
        } finally {
            session.close();
        }
        return user;
    }
    public List<User> findByAnything(String data){

        Session session = HibernateFactory.getSessionFactory().openSession();
        String[]spread=data.split(",");
        List<User>userList=findAll();
        List<User>userListBack=new ArrayList<User>();
User user=null;
        switch(spread[0]){
            case "login":{
                for(User user1:userList){
                    if(user1.getLogin().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }break;
            } case "role":{
                for(User user1:userList){
                    if(user1.getRole().getRoleValue().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }break;
            } case "name":{
                for(User user1:userList){
                    if(user1.getBuyer().getName().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);
                    }
                }break;
            }case "phone":{
                for(User user1:userList){
                    if(user1.getBuyer().getPhoneNumber().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }break;
            }case "sex":{
                for(User user1:userList){
                    if(user1.getBuyer().getSex().getSexValue().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }break;
            }case "country":{
                for(User user1:userList){
                    if(user1.getBuyer().getAddress().getCountry().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }break;
            }case "city":{
                for(User user1:userList){
                    if(user1.getBuyer().getAddress().getCity().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }break;
            }
            case "street":{
                for(User user1:userList){
                    if(user1.getBuyer().getAddress().getStreet().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }break;
            }case "home":{
                for(User user1:userList){
                    if(user1.getBuyer().getAddress().getHouseNumber()==Integer.parseInt(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }break;
            }case "post":{
                for(User user1:userList){
                    if(user1.getBuyer().getAddress().getPostIndex()==Integer.parseInt(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }
                break;
            }
            case "id":{
                for(User user1:userList){
                    if(user1.getUserId()==Integer.parseInt(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }
                break;
            }case "email":{
                for(User user1:userList){
                    if(user1.getBuyer().getEmail().equals(spread[1])){
                        user=user1;
                        userListBack.add(user);

                    }
                }
                break;
            }



        }

        return userListBack;
    }

    public List<User> findAll() {
        Session session = HibernateFactory.getSessionFactory().openSession();
        List<User> userList = null;
        try {
            userList = (List<User>) session.createQuery("FROM User").list();
        } finally {
            session.close();
        }
        return userList;
    }
}
package Service;

import Dao.UserDAO;
import Entities.User;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class UserService implements Service<User>{
    DAO daoService = new UserDAO();

    @Override
    public User findEntity(int id) {
        User entity = (User) daoService.findById(id);

//            entity.getPerson().setPhoneNumber(null);
//            entity.getPerson().setName(null);
//            entity.getPerson().getAddress().setCity(null);
//            entity.getPerson().getAddress().setCountry(null);
//            entity.getPerson().getAddress().setPostIndex(0);
//            entity.getPerson().getAddress().setStreet(null);
//            entity.getPerson().getAddress().setHouseNumber(0);
//            entity.getPerson().setAddress(null);
//            entity.getPerson().getSex().setSexValue(null);
//            entity.getPerson().getSex().setSexId(0);
//            entity.getPerson().setSex(null);
//
//            entity.getPerson().setPersonId(0);

        return entity;
    }

    public List<User> findByAnything(String anything) {
        UserDAO us=new UserDAO();
        List<User>list=us.findByAnything(anything);

        return list;
    }
    @Override
    public void saveEntity(User entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(User entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(User entity) {
        daoService.update(entity);
    }

    @Override
    public List<User> findAllEntities() {

        return daoService.findAll();
    }


}

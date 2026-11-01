package Service;


import Dao.PersonDAO;
import Entities.Buyer;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class PersonService implements Service<Buyer>{
    DAO daoService = new PersonDAO();

    @Override
    public Buyer findEntity(int id) {
        Buyer entity = (Buyer) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Buyer entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Buyer entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Buyer entity) {
        daoService.update(entity);
    }

    @Override
    public List<Buyer> findAllEntities() {
        return daoService.findAll();
    }
}

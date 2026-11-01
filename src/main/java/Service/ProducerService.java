package Service;


import Dao.AddressDAO;
import Dao.CartDAO;
import Dao.PersonDAO;
import Dao.ProducerDAO;
import Entities.Address;
import Entities.Cart;
import Entities.Producer;
import Entities.User;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class ProducerService implements Service<Producer>{
    DAO daoService = new ProducerDAO();

    @Override
    public Producer findEntity(int id) {
        Producer entity = (Producer) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Producer entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Producer entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Producer entity) {
        daoService.update(entity);
    }

    @Override
    public List<Producer> findAllEntities() {
        return daoService.findAll();
    }
}

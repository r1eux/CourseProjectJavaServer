package Service;
import Dao.SexDAO;
import Entities.Sex;
import Entities.User;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class SexService implements Service<Sex>{
    DAO daoService = new SexDAO();

    @Override
    public Sex findEntity(int id) {
        Sex entity = (Sex) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Sex entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Sex entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Sex entity) {
        daoService.update(entity);
    }

    @Override
    public List<Sex> findAllEntities() {
        return daoService.findAll();
    }
}

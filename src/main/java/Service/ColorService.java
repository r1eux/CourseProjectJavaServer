package Service;


import Dao.AddressDAO;
import Dao.ColorDAO;
import Dao.PersonDAO;
import Entities.Address;
import Entities.Color;
import Entities.User;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class ColorService implements Service<Color>{
    DAO daoService = new ColorDAO();

    @Override
    public Color findEntity(int id) {
        Color entity = (Color) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Color entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Color entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Color entity) {
        daoService.update(entity);
    }

    @Override
    public List<Color> findAllEntities() {
        return daoService.findAll();
    }
}

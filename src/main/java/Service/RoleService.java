package Service;
import Dao.RoleDAO;
import Entities.Role;
import Entities.User;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class RoleService implements Service<Role>{
    DAO daoService = new RoleDAO();

    @Override
    public Role findEntity(int id) {
        Role entity = (Role) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Role entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Role entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Role entity) {
        daoService.update(entity);
    }

    @Override
    public List<Role> findAllEntities() {
        return daoService.findAll();
    }
}

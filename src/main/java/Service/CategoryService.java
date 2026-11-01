package Service;

import Dao.CategoryDAO;
import Entities.Category;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class CategoryService implements Service<Category> {
    DAO daoService = new CategoryDAO();

    @Override
    public Category findEntity(int id) {
        return (Category) daoService.findById(id);
    }

    @Override
    public void saveEntity(Category entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Category entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Category entity) {
        daoService.update(entity);
    }

    @Override
    public List<Category> findAllEntities() {
        return daoService.findAll();
    }
}

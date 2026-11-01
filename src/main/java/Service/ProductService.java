package Service;


import Dao.AddressDAO;
import Dao.PersonDAO;
import Dao.ProductDAO;
import Dao.UserDAO;
import Entities.Address;
import Entities.Product;
import Entities.User;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class ProductService implements Service<Product>{
    DAO daoService = new ProductDAO();

    @Override
    public  Product findEntity(int id) {
        Product entity = ( Product) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity( Product entity) {
        daoService.save(entity);
    }
    public List<Product> findByAnything(String anything) {
        ProductDAO us=new ProductDAO();
        List<Product>list=us.findByAnything(anything);

        return list;
    }
    @Override
    public void deleteEntity( Product entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity( Product entity) {
        daoService.update(entity);
    }

    @Override
    public List< Product> findAllEntities() {
        return daoService.findAll();
    }
}

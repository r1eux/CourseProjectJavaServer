package Service;


import Dao.AddressDAO;
import Dao.CartDAO;
import Dao.PersonDAO;
import Entities.Address;
import Entities.Cart;
import Entities.User;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class CartService implements Service<Cart>{
    DAO daoService = new CartDAO();

    @Override
    public Cart findEntity(int id) {
        Cart entity = (Cart) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Cart entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Cart entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Cart entity) {
        daoService.update(entity);
    }

    @Override
    public List<Cart> findAllEntities() {
        return daoService.findAll();
    }
}

package Service;


import Dao.AddressDAO;
import Dao.CartDAO;
import Dao.CartItemDAO;
import Dao.PersonDAO;
import Entities.*;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class CartItemService implements Service<CartItem>{
    DAO daoService = new CartItemDAO();

    @Override
    public CartItem findEntity(int id) {
        CartItem entity = (CartItem) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(CartItem entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(CartItem entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(CartItem entity) {
        daoService.update(entity);
    }

    @Override
    public List<CartItem> findAllEntities() {
        return daoService.findAll();
    }


}

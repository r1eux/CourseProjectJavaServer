package Service;


import Dao.OrderDAO;
import Entities.Order;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class OrderService implements Service<Order>{
    DAO daoService = new OrderDAO();

    @Override
    public Order findEntity(int id) {
        Order entity = (Order) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Order entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Order entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Order entity) {
        daoService.update(entity);
    }

    @Override
    public List<Order> findAllEntities() {
        return daoService.findAll();
    }
}

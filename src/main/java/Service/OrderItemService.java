package Service;


import Dao.OrderItemDAO;
import Entities.OrderItem;
import Interfaces.DAO;
import Interfaces.Service;

import java.util.List;

public class OrderItemService implements Service<OrderItem>{
    DAO daoService = new OrderItemDAO();

    @Override
    public OrderItem findEntity(int id) {
        OrderItem entity = (OrderItem) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(OrderItem entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(OrderItem entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(OrderItem entity) {
        daoService.update(entity);
    }

    @Override
    public List<OrderItem> findAllEntities() {
        return daoService.findAll();
    }
}

package model.dataaccessunit;

import model.entity.Order;

import java.sql.Connection;
import java.util.List;
//TODO
public class OrderDAO extends DAO<Order>{

    protected OrderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Order getEntityByID(long ID) {
        return null;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void create(Order entity) {

    }
}

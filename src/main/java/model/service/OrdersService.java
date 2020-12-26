package model.service;

import model.dataaccessunit.*;
import model.entity.Order;

import java.sql.SQLException;
import java.util.List;

public class OrdersService {
    OrdersService() {
    }

    List<Order> getAllCustomerOrders(int customerId) throws SQLException {
        List<Order> orders = null;
        try {
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAO(DAOFactory.Entities.ORDER, ConnectionPool.getConnection());
            orders = orderDAO.getOrdersByUserID(customerId);
            AutomobileDAO automobileDAO= (AutomobileDAO) DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, ConnectionPool.getConnection());
            for (Order order : orders){
                order.setAutomobile(automobileDAO.getEntityByID(order.getAutomobileID()));
            }
            ConnectionPool.closeConnection();
        } catch (SQLException throwables) {
            throw new SQLException("No connection to DB");
        }
        return orders;
    }

    Order getOrderById(int ID){
         Order order = null;
        try {
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAO(DAOFactory.Entities.ORDER, ConnectionPool.getConnection());
            order = orderDAO.getEntityByID(ID);
            BillDAO billDAO = (BillDAO) DAOFactory.getDAO(DAOFactory.Entities.BILL, ConnectionPool.getConnection());
            order.setBill(billDAO.getEntityByID(order.getId()));
            AutomobileDAO automobileDAO = (AutomobileDAO) DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, ConnectionPool.getConnection());
            order.setAutomobile(automobileDAO.getEntityByID(order.getAutomobileID()));
            ConnectionPool.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }
}

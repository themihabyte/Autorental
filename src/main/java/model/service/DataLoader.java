package model.service;

import model.dataaccessunit.*;
import model.entity.Automobile;
import model.entity.Order;
import model.entity.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {
    public List<String> getManufacturers() throws SQLException {
        List<String> manufacturers = null;
        try {
            AutomobileDAO automobileDAO = (AutomobileDAO) DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, ConnectionPool.getConnection());
            manufacturers = automobileDAO.getManufacturers();
            ConnectionPool.closeConnection();
        } catch (SQLException throwables) {
            throw new SQLException("No connection to DB");
        }

        return manufacturers;
    }
    public List<Automobile> getAllAutomobiles() throws SQLException {
        List<Automobile> automobiles;
        try {
            AutomobileDAO automobileDAO = (AutomobileDAO) DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, ConnectionPool.getConnection());
            automobiles = automobileDAO.getAll();
            ConnectionPool.closeConnection();
        } catch (SQLException throwables) {
            throw new SQLException("No connection to DB");
        }

        return automobiles;
    }
    public Order getOrderById(int ID){
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
    List<Order> getAllCustomerOrders(int customerId) throws SQLException {
        List<Order> orders = null;
        try {
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAO(DAOFactory.Entities.ORDER, ConnectionPool.getConnection());
            orders = orderDAO.getOrdersByUserID(customerId);
            AutomobileDAO automobileDAO= (AutomobileDAO) DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, ConnectionPool.getConnection());
            BillDAO billDAO = (BillDAO) DAOFactory.getDAO(DAOFactory.Entities.BILL, ConnectionPool.getConnection());
            for (Order order : orders){
                order.setAutomobile(automobileDAO.getEntityByID(order.getAutomobileID()));
                order.setBill(billDAO.getEntityByID(order.getId()));
            }
            ConnectionPool.closeConnection();
        } catch (SQLException throwables) {
            throw new SQLException("No connection to DB");
        }
        return orders;
    }
    List<User> getAllCustomers() throws SQLException {
        AuthorizedUserDAO userDAO = (AuthorizedUserDAO) DAOFactory.getDAO(DAOFactory.Entities.USER, ConnectionPool.getConnection());
        List<User> users = userDAO.getAllCustomers();
        ConnectionPool.closeConnection();
        return users;
    }
    public Map<User, List<Order>> getAllCustomersAndTheirOrders() throws SQLException {
        Map<User, List<Order>> userOrdersMap = new HashMap<>();
        List<User> users = getAllCustomers();
        for(User customer: users){
            userOrdersMap.put(customer, getAllCustomerOrders(customer.getId()));
        }
        return userOrdersMap;
    }
}

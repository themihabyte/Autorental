package model.service;

import model.dataaccessunit.*;
import model.entity.Automobile;
import model.entity.Bill;
import model.entity.Order;
import model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserManagerService extends AuthorizedUserService {
    protected UserManagerService(User user) {
        super(user);
    }

    public void denyOrder(int orderID, String reason) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        connection.setAutoCommit(false);
        try {
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAO(DAOFactory.Entities.ORDER, connection);
            Order order = orderDAO.getEntityByID(orderID);
            order.setDenied(true);
            order.setRejectionReason(reason);
            orderDAO.update(order);

            AutomobileDAO automobileDAO = (AutomobileDAO) DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, connection);
            Automobile automobile = automobileDAO.getEntityByID(order.getAutomobileID());
            automobile.setInStock(true);
            automobileDAO.update(automobile);

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException sqlException) {
            connection.rollback();
            throw sqlException;
        } finally {
            connection.close();
        }
    }

    public void receiveAutomobileBack(int automobileID, int billID, float price) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        connection.setAutoCommit(false);

        try {
            AutomobileDAO automobileDAO = (AutomobileDAO) DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, connection);
            Automobile automobile = automobileDAO.getEntityByID(automobileID);
            automobile.setInStock(true);
            automobileDAO.update(automobile);

            BillDAO billDAO = (BillDAO) DAOFactory.getDAO(DAOFactory.Entities.BILL, connection);
            Bill bill = billDAO.getEntityByID(billID);
            if (!bill.isPayed()){
                bill.setPrice(bill.getPrice()+price);
            } else {
                bill.setPrice(price);
            }
            bill.setPayed(false);
            billDAO.update(bill);

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException sqlException) {
            connection.rollback();
            sqlException.printStackTrace();
        } finally {
            connection.close();
        }
    }
}

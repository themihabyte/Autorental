package model.service;

import model.dataaccessunit.ConnectionPool;
import model.dataaccessunit.DAOFactory;
import model.entity.Automobile;
import model.entity.Bill;
import model.entity.Order;
import model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UserCustomerService extends AuthorizedUserService {

    protected UserCustomerService(User user) {
        super(user);
    }

    public int makeOrder(String passportDetails, String startDate, String endDate, boolean isHasDriver, int automobileID) throws SQLException {
        Order order = new Order(this.user.getId(), automobileID,
                passportDetails, startDate, endDate, isHasDriver, false);
        Automobile automobile;

        Connection connection = ConnectionPool.getConnection();
        connection.setAutoCommit(false);
        try {
            orderDAO = DAOFactory.getDAO(DAOFactory.Entities.ORDER, connection);
            int orderId;
            orderId = orderDAO.create(order);

            if (orderId != -1) {
                order.setId(orderId);
            }

            automobileDAO = DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, connection);
            automobile = (Automobile) automobileDAO.getEntityByID(automobileID);
            automobile.setInStock(false);
            automobileDAO.update(automobile);

            float price = ChronoUnit.DAYS.between(LocalDate.parse(startDate), LocalDate.parse(endDate)) * automobile.getPrice();
            Bill bill = new Bill(orderId, price, false);
            billDAO = DAOFactory.getDAO(DAOFactory.Entities.BILL, connection);
            billDAO.create(bill);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            connection.rollback();
            throw new SQLException("Something went wrong, this order haven`t been created");
        } finally {
            connection.close();
        }

        return order.getId();
    }

    public void payBill(int billID) throws SQLException {
        try {
            billDAO = DAOFactory.getDAO(DAOFactory.Entities.BILL, ConnectionPool.getConnection());
            Bill bill = (Bill) billDAO.getEntityByID(billID);
            bill.setPayed(true);
            billDAO.update(bill);
            ConnectionPool.closeConnection();
        } catch (SQLException sqlException) {
            throw new SQLException("no connection to DataBase");
        }
    }

    public List<Order> getAllCustomerOrders() throws SQLException {
        return new DataLoader().getAllCustomerOrders(this.user.getId());
    }

    public Order getOrderById(int ID){
        return new DataLoader().getOrderById(ID);
    }
}

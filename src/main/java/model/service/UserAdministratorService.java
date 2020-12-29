package model.service;
import model.dataaccessunit.AuthorizedUserDAO;
import model.dataaccessunit.AutomobileDAO;
import model.dataaccessunit.ConnectionPool;
import model.dataaccessunit.DAOFactory;
import model.entity.Automobile;
import model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserAdministratorService extends AuthorizedUserService {

    protected UserAdministratorService(User user) {
        super(user);
    }

    public void addAutomobile(Automobile.Segment segment, String name, String manufacturer, float price, boolean isInStock) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        connection.setAutoCommit(false);
        try {
            AutomobileDAO automobileDAO = (AutomobileDAO) DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, connection);
            automobileDAO.create(new Automobile(segment, name, manufacturer, price, isInStock));
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException sqlException) {
            connection.rollback();
            throw new SQLException("No connection to DataBase");
        } finally {
            connection.close();
        }
    }
    public void updateAutomobile(int id, Automobile.Segment segment, String name, String manufacturer, float price, boolean isInStock) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        connection.setAutoCommit(false);
        try {
            AutomobileDAO automobileDAO = (AutomobileDAO) DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, connection);
            automobileDAO.update(new Automobile(id, segment, name, manufacturer,
                    price, isInStock));
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException sqlException) {
            connection.rollback();
            throw sqlException;
        } finally {
            connection.close();
        }
    }

    public void deleteAutomobile(int automobileID) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        connection.setAutoCommit(false);
        try {
            AutomobileDAO automobileDAO = (AutomobileDAO) DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, ConnectionPool.getConnection());
            automobileDAO.delete(automobileID);
            connection.setAutoCommit(true);
        } catch (SQLException sqlException){
            connection.rollback();
            throw sqlException;
        } finally {
            connection.close();
        }
    }

    public void changeCustomerBanStatus(int customerID) throws SQLException {
        try {
            AuthorizedUserDAO userDAO = (AuthorizedUserDAO) DAOFactory.getDAO(DAOFactory.Entities.USER, ConnectionPool.getConnection());
            User user = userDAO.getEntityByID(customerID);
            user.setBanned(!user.isBanned());
            userDAO.update(user);
        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }

    public void registerManager(String username, String password) throws SQLException {
        new RegistrationService().registerUser(username, password, UserServiceFactory.UserRole.MANAGER);
    }
}

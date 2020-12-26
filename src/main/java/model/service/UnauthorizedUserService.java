package model.service;

import model.dataaccessunit.AuthorizedUserDAO;
import model.dataaccessunit.ConnectionPool;
import model.dataaccessunit.DAOFactory;
import model.entity.User;

import java.sql.SQLException;

public class UnauthorizedUserService implements UserService {
    private AuthorizedUserDAO userDAO;

    protected UnauthorizedUserService() {
    }

    public UserService login(String username, String password) throws SQLException {
        try {
            userDAO = (AuthorizedUserDAO) DAOFactory.getDAO(DAOFactory.Entities.USER, ConnectionPool.getConnection());
        } catch (SQLException throwables) {
            throw new SQLException("No connection to DataBase");
        }
        User user;
        try {
            user = userDAO.findByUsernamePassword(username, password);
        } catch (SQLException throwables) {
            throw new SQLException("Invalid username or password");
        }
        return UserServiceFactory.getUserService(user);
    }

    public void register(String username, String password) throws SQLException {
        try {
            new RegistrationService().registerUser(username, password, UserServiceFactory.UserRole.CUSTOMER);
        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }
}

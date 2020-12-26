package model.service;

import model.dataaccessunit.AuthorizedUserDAO;
import model.dataaccessunit.ConnectionPool;
import model.dataaccessunit.DAOFactory;
import model.entity.User;

import java.sql.SQLException;

public class RegistrationService {
    private AuthorizedUserDAO userDAO;

    RegistrationService() {
    }

    void registerUser(String username, String password,
                      UserServiceFactory.UserRole role) throws SQLException {
        try {
            userDAO = (AuthorizedUserDAO) DAOFactory.getDAO(DAOFactory.Entities.USER, ConnectionPool.getConnection());
            userDAO.create(new User(username, password, role));
            ConnectionPool.closeConnection();
        } catch (SQLException throwables) {
            throw new SQLException("No connection to Data Base");
        }
    }
}

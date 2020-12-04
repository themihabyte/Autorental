package model.service;

import model.dataaccessunit.AuthorizedUserDAO;
import model.dataaccessunit.ConnectionPool;
import model.dataaccessunit.DAOFactory;
import model.entity.User;

import java.sql.SQLException;

public class RegistrationService {
    private static AuthorizedUserDAO userDAO;

    public static void registerUser(String username, String password,
                                    UserServiceFactory.UserRole role){
        try {
            userDAO = (AuthorizedUserDAO) DAOFactory.getDAO(DAOFactory.Entities.USER, ConnectionPool.getConnection());
        } catch (SQLException throwables) {
            //TODO
        }
        try {
            userDAO.create(new User(username, password, role));
        } catch (SQLException throwables) {
            //TODO
        }
    }
}

package model.service;

import model.dataaccessunit.AuthorizedUserDAO;
import model.dataaccessunit.ConnectionPool;
import model.dataaccessunit.DAOFactory;
import model.entity.User;

import java.sql.SQLException;

//TODO
public class UnauthorizedUserService implements UserService {
    private AuthorizedUserDAO userDAO;
    protected UnauthorizedUserService() {
    }

    public UserService login(String username, String password){
        try {
            userDAO = (AuthorizedUserDAO) DAOFactory.getDAO(DAOFactory.Entities.USER, ConnectionPool.getConnection());
        } catch (SQLException throwables) {
            //TODO
        }
        User user = null;
        try {
            user = userDAO.findByUsernamePassword(username, password);
        } catch (SQLException throwables) {
            //TODO
            throwables.printStackTrace();
            System.out.println("Poshel nahui");
        }
        return UserServiceFactory.getUserService(user);
    }

    public void register(String username, String password){
        RegistrationService.registerUser(username, password, UserServiceFactory.UserRole.CUSTOMER);
    }
}

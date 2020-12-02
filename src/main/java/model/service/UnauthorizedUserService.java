package model.service;

import model.dataaccessunit.ConnectionPool;
import model.dataaccessunit.DAO;
import model.dataaccessunit.DAOFactory;
import model.entity.User;

import java.sql.SQLException;

//TODO
public class UnauthorizedUserService implements Service{
    DAO userDAO;
    DAO automobileDAO;


    protected UnauthorizedUserService() {
    }

    public void login(String username, String password){

    }

    public User register(String username, String password){
        return new User(username, password, User.Role.CUSTOMER);
    }
}

package model.service;

import model.dataaccessunit.ConnectionPool;
import model.dataaccessunit.DAO;
import model.dataaccessunit.DAOFactory;
import model.entity.Automobile;
import model.entity.User;

//TODO
public class UnauthorizedUserService implements Service{
    DAO<User> userDAO;
    DAO<Automobile> automobileDAO;


    protected UnauthorizedUserService() {
    }

    public void login(String username, String password){

    }

    public User register(String username, String password){
        return new User(username, password, User.Role.CUSTOMER);
    }
}

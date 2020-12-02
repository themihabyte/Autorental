package model.service;

import model.dataaccessunit.*;
import model.entity.User;
//TODO
// create DAO in methods
public abstract class AuthorizedUserService implements Service{
    User user;
    ConnectionPool connectionPool;
    DAO userDAO;
    DAO automobileDAO;
    DAO billDAO;
    DAO orderDAO;

    protected AuthorizedUserService(String username, String password, User.Role role) {
        this.user = new User(username, password, role);
    }
}

package model.service;

import model.dataaccessunit.*;
import model.entity.User;


public abstract class AuthorizedUserService implements UserService {
    protected User user;
    DAO automobileDAO;
    DAO billDAO;
    DAO orderDAO;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected AuthorizedUserService(User user) {
        this.user = user;
    }
}

package model.service;

import model.entity.User;

//TODO
public class UserManagerService extends AuthorizedUserService {
    protected UserManagerService(User user) {
        super(user);
    }

    public void denyOrder(long orderID){

    }

    public void receiveAutomobileBack(long automobileID){

    }
}

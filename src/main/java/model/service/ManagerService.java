package model.service;

import model.entity.User;
//TODO
public class ManagerService extends AuthorizedUserService {
    protected ManagerService(String username, String password, User.Role role) {
        super(username, password, role);
    }

    public void denyOrder(long orderID){

    }

    public void receiveAutomobileBack(long automobileID){

    }
}

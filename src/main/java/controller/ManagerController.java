package controller;

import model.entity.User;
//TODO
public class ManagerController extends Controller{
    public ManagerController(String username, String password) {
        super(User.Role.MANAGER, username, password);
    }

    @Override
    public void run() {

    }
}

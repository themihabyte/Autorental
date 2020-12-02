package controller;

import model.entity.User;
//TODO
public class AdministratorController extends Controller{
    public AdministratorController(String username, String password) {
        super(User.Role.ADMINISTRATOR, username, password);
    }

    @Override
    public void run() {

    }
}

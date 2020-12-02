package controller;

import model.entity.User;
//TODO
public class CustomerController extends Controller{
    public CustomerController(String username, String password) {
        super(User.Role.CUSTOMER, username, password);
    }

    @Override
    public void run() {

    }
}

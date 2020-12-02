package controller;

import model.entity.User;
import model.service.Service;
import model.service.ServiceFactory;
import view.View;
//TODO service in "run"
public abstract class Controller {
    View view;
    public abstract void run();

    public Controller(User.Role role, String username, String password) {
        this.view = new View();
    }
}

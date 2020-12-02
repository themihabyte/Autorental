package model.service;

import model.entity.User;

public class ServiceFactory {
    public static Service getUserService(User.Role role, String username, String password){
        switch (role){
            case MANAGER: return new ManagerService(username, password, role);
            case CUSTOMER: return new CustomerService(username, password, role);
            case ADMINISTRATOR: return new AdministratorService(username, password, role);
            default: return new UnauthorizedUserService();
        }
    }

}

package model.service;

import model.entity.User;

public class UserServiceFactory {
    public static UserService getUserService(User user){
        if (user == null){
            return new UnauthorizedUserService();
        } else {
            switch (user.getRole()) {
                case MANAGER:
                    return new UserManagerService(user);
                case CUSTOMER:
                    return new UserCustomerService(user);
                case ADMINISTRATOR:
                    return new UserAdministratorService(user);
                case UNAUTHORIZED_USER:
                    return new UnauthorizedUserService();
            }
        }
        return null;
    }
    public static UserService getUserService(){
        return new UnauthorizedUserService();
    }
    public enum UserRole {ADMINISTRATOR, MANAGER, CUSTOMER, UNAUTHORIZED_USER}
}

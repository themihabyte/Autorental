package model.service;

import model.entity.User;

import java.sql.SQLException;

public class UserServiceFactory {
    public static UserService getUserService(User user) throws SQLException {
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
    public static UserService getUserService() throws SQLException {
        return new UnauthorizedUserService();
    }
    public enum UserRole {ADMINISTRATOR, MANAGER, CUSTOMER, UNAUTHORIZED_USER}
}

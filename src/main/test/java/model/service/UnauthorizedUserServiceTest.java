package model.service;

import model.dataaccessunit.AuthorizedUserDAO;
import model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.sql.SQLException;

class UnauthorizedUserServiceTest {
    private UnauthorizedUserService service;
    private AuthorizedUserDAO userDAO;

    @BeforeEach
    void setUp() {
        service = new UnauthorizedUserService();
        userDAO = Mockito.mock(AuthorizedUserDAO.class);

        try {
            Field privateField = service.getClass().getDeclaredField("userDAO");
            privateField.setAccessible(true);
            privateField.set(service, userDAO);
            privateField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void login() {
        String username = Mockito.anyString();
        String password = Mockito.anyString();
        User user = new User(username, password, UserServiceFactory.UserRole.CUSTOMER);
        try {
            Mockito.when(userDAO.findByUsernamePassword(username,
                    password)).thenReturn(user);
            AuthorizedUserService userService= (AuthorizedUserService) service.login(username, password);
            Assertions.assertEquals(user, userService.getUser());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
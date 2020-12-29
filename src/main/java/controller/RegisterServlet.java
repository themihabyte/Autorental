package controller;

import model.service.UnauthorizedUserService;
import model.service.UserAdministratorService;
import model.service.UserService;
import view.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Register", urlPatterns = {"/register-servlet"})
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        if (!validateUsernamePassword(httpServletRequest, httpServletResponse,
                username, password)) return;
        if (!registerUser(httpServletRequest, httpServletResponse,
                username, password)) return;
        httpServletResponse.sendRedirect("/start-page");
    }

    private boolean validateUsernamePassword(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                          String username, String password) throws ServletException, IOException {
        Validator validator = new Validator();
        try {
            validator.validateUsernamePassword(username, password);
        } catch (RuntimeException validationError){
            new ErrorSender().sendErrorToJSP(httpServletRequest, httpServletResponse,
                    validationError.getMessage(), "/register");
            return false;
        }
        return true;
    }
    private boolean registerUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                              String username, String password) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        UserService service = (UserService) session.getAttribute("service");
        if (service instanceof UnauthorizedUserService){
            try {
                ((UnauthorizedUserService) service).register(username, password);
            } catch (SQLException sqlException) {
                new ErrorSender().sendErrorToJSP(httpServletRequest, httpServletResponse,
                        sqlException.getMessage(), "/register-page");
                return false;
            }
        } else if (service instanceof UserAdministratorService){
            try {
                ((UserAdministratorService) service).registerManager(username, password);
            } catch (SQLException sqlException) {
                new ErrorSender().sendErrorToJSP(httpServletRequest, httpServletResponse,
                        sqlException.getMessage(), "/register-page");
            }
        }

        return true;
    }
}

package controller;

import model.service.UnauthorizedUserService;
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
        if (!(validator.validateUsername(username) && validator.validatePassword(password))){
            String errorMessage = "Invalid username or password";
            httpServletRequest.setAttribute("error_message", errorMessage);
            httpServletRequest
                    .getRequestDispatcher("/register")
                    .forward(httpServletRequest, httpServletResponse);
            return false;
        }
        return true;
    }
    private boolean registerUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                              String username, String password) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        UnauthorizedUserService service = (UnauthorizedUserService) session.getAttribute("service");
        try {
            service.register(username, password);
        } catch (SQLException sqlException) {
            String errorMessage = sqlException.getMessage();
            httpServletRequest.setAttribute("error_message", errorMessage);
            httpServletRequest
                    .getRequestDispatcher("/register-page")
                    .forward(httpServletRequest, httpServletResponse);
            return false;
        }
        return true;
    }
}

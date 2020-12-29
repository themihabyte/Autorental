package controller;

import model.service.UnauthorizedUserService;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Login", urlPatterns = {"/login-servlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        HttpSession session = httpServletRequest.getSession();
        UserService userService = (UserService) session.getAttribute("service");
        if (userService instanceof UnauthorizedUserService){
            if (!login(httpServletRequest, httpServletResponse, session, username, password,
                    userService)) return;
        }
        httpServletResponse.sendRedirect("/start-page");
    }
    private boolean login(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse, HttpSession session,
                          String username, String password, UserService userService) throws ServletException, IOException {
        UserService service;
        try {
            service = ((UnauthorizedUserService) userService).login(username, password);
        } catch (SQLException sqlException) {
            new ErrorSender().sendErrorToJSP(httpServletRequest,
                    httpServletResponse, sqlException.getMessage(), "/login");
            return false;
        }
        session.setAttribute("service", service);
        return true;
    }
}

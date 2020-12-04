package controller;


import model.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Main", urlPatterns = {"/Main"})
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        UserService service = (UserService) session.getAttribute("service");
        if (service==null){
           service = UserServiceFactory.getUserService(null);
        }
        session.setAttribute("service", service);
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/start-page");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }
}

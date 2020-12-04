package controller;

import model.dataaccessunit.DAOFactory;
import model.service.UnauthorizedUserService;
import model.service.UserService;
import model.service.UserServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Map<String, String[]> params = httpServletRequest.getParameterMap();
        HttpSession session = httpServletRequest.getSession();
        UnauthorizedUserService unauthorizedUserService = (UnauthorizedUserService) session.getAttribute("service");
        UserService service = unauthorizedUserService.login(params.get("username")[0], params.get("password")[0]);
        session.setAttribute("service", service);
        httpServletResponse.sendRedirect("/Main");
    }
}

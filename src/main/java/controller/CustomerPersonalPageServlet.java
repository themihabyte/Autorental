package controller;

import model.entity.Order;
import model.service.UserCustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/customer-personal-page-servlet"})
public class CustomerPersonalPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        UserCustomerService service = (UserCustomerService) session.getAttribute("service");
        List<Order> orders = null;
        try {
            orders = service.getAllCustomerOrders();
        } catch (SQLException sqlException) {
            new ErrorSender().sendErrorToJSP(httpServletRequest,
                    httpServletResponse, sqlException.getMessage(), "/customer-personal-page");
            return;
        }
        httpServletRequest.setAttribute("list", orders);

        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/customer-personal-page");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }
}

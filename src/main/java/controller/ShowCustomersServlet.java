package controller;

import model.entity.Order;
import model.entity.User;
import model.service.DataLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/show-customers-servlet")
public class ShowCustomersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        DataLoader dataLoader = new DataLoader();
        Map<User, List<Order>> userOrdersMap = null;
        try {
            userOrdersMap = dataLoader
                    .getAllCustomersAndTheirOrders();
        } catch (SQLException sqlException) {
            new ErrorSender().sendErrorToJSP(httpServletRequest, httpServletResponse,
                    sqlException.getMessage(), "/users-orders");
        }

        httpServletRequest.setAttribute("usersOrdersMap", userOrdersMap);
        httpServletRequest
                .getRequestDispatcher("/users-orders")
                .forward(httpServletRequest, httpServletResponse);
    }
}

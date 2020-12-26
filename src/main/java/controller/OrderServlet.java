package controller;

import model.entity.Order;
import model.service.UserCustomerService;
import model.service.UserManagerService;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/order-servlet")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        deliverOrderToJSP(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        deliverOrderToJSP(httpServletRequest, httpServletResponse);
    }

    private void deliverOrderToJSP(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        UserService service = (UserService) session.getAttribute("service");
        if (service instanceof UserManagerService) {
            // TODO deny and register automobile back
        } else if (service instanceof UserCustomerService) {
            String orderId = httpServletRequest.getParameter("order_id");
            if (orderId == null) {
                orderId = httpServletRequest.getAttribute("order_id").toString();
            }
            Order order = ((UserCustomerService) service).getOrderById(Integer.parseInt(orderId));
            httpServletRequest.setAttribute("order", order);
        }
        httpServletRequest.getRequestDispatcher("/order")
                .forward(httpServletRequest, httpServletResponse);
    }
}

package controller;

import model.entity.Order;
import model.service.DataLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String orderId = httpServletRequest.getParameter("order_id");
        if (orderId == null) {
            orderId = httpServletRequest.getAttribute("order_id").toString();
        }
        Order order = new DataLoader().getOrderById(Integer.parseInt(orderId));
        httpServletRequest.setAttribute("order", order);

        httpServletRequest.getRequestDispatcher("/order")
                .forward(httpServletRequest, httpServletResponse);
    }
}

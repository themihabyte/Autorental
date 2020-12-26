package controller;

import model.service.UserCustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet (urlPatterns = {"/payment-servlet"})
public class PaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        UserCustomerService service = (UserCustomerService) session
                .getAttribute("service");

        int orderID = Integer
                .parseInt(httpServletRequest.getParameter("bill_id"));
        try{
            service.payBill(orderID);
        } catch (SQLException sqlException){
            httpServletRequest.setAttribute("payment_error_message", sqlException.getMessage());
        }
        httpServletRequest.setAttribute("order_id", orderID);
        httpServletRequest
                .getRequestDispatcher("/order-servlet")
                .forward(httpServletRequest, httpServletResponse);
    }
}

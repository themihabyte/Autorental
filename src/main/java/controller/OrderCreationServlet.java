package controller;

import model.service.UserCustomerService;
import view.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(urlPatterns = {"/create-order-servlet"})
public class OrderCreationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Map<String, String[]> params = httpServletRequest.getParameterMap();
        params.putIfAbsent("isHasDriver", new String[]{"false"});

        HttpSession session = httpServletRequest.getSession();
        UserCustomerService service = (UserCustomerService) session.getAttribute("service");

        try {
            validateParameters(params);
        } catch (RuntimeException runtimeException){
            new ErrorSender().sendErrorToJSP(httpServletRequest, httpServletResponse,
                    runtimeException.getMessage(), "/create-order");
            return;
        }

        int orderID;
        try {
            orderID = service.makeOrder(params.get("passportDetails")[0],
                    params.get("date")[0],
                    params.get("date")[1], Boolean.parseBoolean(params.get("isHasDriver")[0]),
                    Integer.parseInt(params.get("autoId")[0]));
        } catch (SQLException sqlException) {
            new ErrorSender().sendErrorToJSP(httpServletRequest,
                    httpServletResponse, sqlException.getMessage(), "/create-order");
            return;
        }

        httpServletRequest.removeAttribute("passportDetails");
        httpServletRequest.removeAttribute("date");
        httpServletRequest.removeAttribute("isHasDriver");
        httpServletRequest.removeAttribute("autoId");
        httpServletRequest.setAttribute("order_id", orderID);

        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/order-servlet");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }

    private void validateParameters(Map<String, String[]> params) {
        Validator validator = new Validator();
        validator.validatePassportDetails(params.get("passportDetails")[0]);
        validator.validateDate(params.get("date")[0],
                params.get("date")[1]);
    }
}

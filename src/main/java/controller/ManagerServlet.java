package controller;

import model.service.UserManagerService;
import view.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


@WebServlet(urlPatterns = "/manager-servlet")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String action = httpServletRequest.getParameter("action");
        switch (action){
            case "show_customers":
                httpServletRequest.getRequestDispatcher("/show-customers-servlet")
                        .forward(httpServletRequest, httpServletResponse);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String action = httpServletRequest.getParameter("action");
        switch (action){
            case "deny_order":
                if (denyOrder(httpServletRequest, httpServletResponse)){
                    httpServletResponse.sendRedirect("/start-page");
                }
                break;
            case "receive_automobile":
                if (receiveAutomobileBack(httpServletRequest, httpServletResponse)){
                    httpServletResponse.sendRedirect("/start-page");
                }
                break;
        }
    }
    private boolean denyOrder(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Map<String, String[]> params = httpServletRequest.getParameterMap();
        HttpSession session = httpServletRequest.getSession();
        UserManagerService service = (UserManagerService) session.getAttribute("service");

        try {
            Validator validator= new Validator();
            validator.validateReason(params.get("rejection_reason")[0]);
            service.denyOrder(Integer.parseInt(params.get("order_id")[0]),
                    params.get("rejection_reason")[0]);
        } catch (RuntimeException | SQLException exception){
            new ErrorSender().sendErrorToJSP(httpServletRequest,
                    httpServletResponse, exception.getMessage(),
                    "/order");
            return false;
        }
        return true;
    }
    private boolean receiveAutomobileBack(HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Map<String, String[]> params = httpServletRequest.getParameterMap();
        HttpSession session = httpServletRequest.getSession();
        UserManagerService service = (UserManagerService) session.getAttribute("service");

        try{
            Validator validator = new Validator();
            validator.validatePrice(params.get("bill_price")[0]);
            service.receiveAutomobileBack(Integer.parseInt(params.get("automobile_id")[0]) ,
                    Integer.parseInt(params.get("bill_id")[0]),
                    Float.parseFloat(params.get("bill_price")[0]));
        } catch (SQLException |RuntimeException exception) {
            new ErrorSender().sendErrorToJSP(httpServletRequest,
                    httpServletResponse, exception.getMessage(),
                    "/order");
            return false;
        }
        return true;
    }
}

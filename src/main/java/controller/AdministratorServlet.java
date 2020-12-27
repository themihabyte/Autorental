package controller;

import model.entity.Automobile;
import model.entity.Order;
import model.entity.User;
import model.service.DataLoader;
import model.service.ErrorSender;
import model.service.UserAdministratorService;
import view.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/administrator-servlet")
public class AdministratorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        switch (httpServletRequest.getParameter("action")) {
            case "add_automobile":
                if (addAutomobile(httpServletRequest, httpServletResponse)) {
                    httpServletResponse.sendRedirect("/start-page");
                }
                break;
            case "update_automobile":
                if (updateAutomobile(httpServletRequest, httpServletResponse)){
                    httpServletResponse.sendRedirect("/start-page");
                };
                break;
            case "delete_automobile":
                if (deleteAutomobile(httpServletRequest, httpServletResponse)) {
                    httpServletResponse.sendRedirect("/start-page");
                }
                break;
            case "ban_customer":
                if (banCustomer(httpServletRequest, httpServletResponse)) {
                    httpServletResponse.sendRedirect("/users-orders");
                }
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        switch (httpServletRequest.getParameter("action")) {
            case "show_customers":
                showCustomers(httpServletRequest, httpServletResponse);
                break;
        }
    }

    private void showCustomers(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
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

    private boolean addAutomobile(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        UserAdministratorService service = (UserAdministratorService) session.getAttribute("service");
        Map<String, String[]> params = httpServletRequest.getParameterMap();
        params.putIfAbsent("is_in_stock", new String[]{"false"});
        Validator validator = new Validator();
        try {
            validator.validatePrice(params.get("price")[0]);
            validator.validateName(params.get("manufacturer")[0]);
            validator.validateName(params.get("name")[0]);
        } catch (RuntimeException runtimeException) {
            new ErrorSender().sendErrorToJSP(httpServletRequest,
                    httpServletResponse, runtimeException.getMessage(),
                    "/add-automobile");
            return false;
        }

        try {
            service.addAutomobile(Automobile.Segment.valueOf(params.get("segment")[0]),
                    params.get("name")[0], params.get("manufacturer")[0],
                    Float.parseFloat(params.get("price")[0]),
                    Boolean.parseBoolean(params.get("is_in_stock")[0]));
        } catch (SQLException sqlException) {
            new ErrorSender().sendErrorToJSP(httpServletRequest, httpServletResponse,
                    sqlException.getMessage(), "/add-automobile");
            return false;
        }
        return true;
    }

    private boolean deleteAutomobile(HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        UserAdministratorService service = (UserAdministratorService) session.getAttribute("service");
        try {
            service.deleteAutomobile(Integer.parseInt(httpServletRequest.getParameter("automobile_id")));
        } catch (SQLException sqlException) {
            new ErrorSender().sendErrorToJSP(httpServletRequest, httpServletResponse,
                    sqlException.getMessage(), "/start-page");
            return false;
        }
        return true;
    }

    private boolean banCustomer(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        UserAdministratorService service = (UserAdministratorService) session.getAttribute("service");
        try {
            service.banCustomer(Integer.parseInt(httpServletRequest.getParameter("customer_id")));
        } catch (SQLException sqlException) {
            new ErrorSender().sendErrorToJSP(httpServletRequest, httpServletResponse,
                    sqlException.getMessage(), "/users-orders");
            return false;
        }
        return true;
    }

    private boolean updateAutomobile(HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        UserAdministratorService service = (UserAdministratorService) session.getAttribute("service");
        Map<String, String[]> params = httpServletRequest.getParameterMap();
        params.putIfAbsent("is_in_stock", new String[]{"false"});
        Validator validator = new Validator();
        try {
            validator.validatePrice(params.get("price")[0]);
            validator.validateName(params.get("manufacturer")[0]);
            validator.validateName(params.get("name")[0]);
        } catch (RuntimeException runtimeException) {
            new ErrorSender().sendErrorToJSP(httpServletRequest,
                    httpServletResponse, runtimeException.getMessage(),
                    "/add-automobile");
            return false;
        }
        try {
            service.updateAutomobile(Integer.parseInt(params.get("automobile_id")[0]),
                    Automobile.Segment.valueOf(params.get("segment")[0]),
                    params.get("name")[0], params.get("manufacturer")[0],
                    Float.parseFloat(params.get("price")[0]),
                    Boolean.parseBoolean(params.get("is_in_stock")[0]));
        } catch (SQLException sqlException) {
            new ErrorSender().sendErrorToJSP(httpServletRequest, httpServletResponse,
                    sqlException.getMessage(), "/add-automobile");
            return false;
        }
        return true;
    }
}

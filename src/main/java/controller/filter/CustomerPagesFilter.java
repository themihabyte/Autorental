package controller.filter;

import model.service.UserCustomerService;
import model.service.UserManagerService;
import model.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomerPagesFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        UserService service = (UserService) session.getAttribute("service");
        if (!(service instanceof UserCustomerService)){
            res.sendRedirect("/access-denied.html");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(req.getRequestURI());
            dispatcher.forward(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}

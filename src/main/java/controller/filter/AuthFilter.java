package controller.filter;

import model.service.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/start-page"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        UserService service = (UserService) session.getAttribute("service");
        if (service==null){
            service = UserServiceFactory.getUserService();
            session.setAttribute("service", service);
        } else if (service instanceof UserCustomerService && ((UserCustomerService) service)
                .getUser().isBanned()) {
                res.sendRedirect("/access-denied.html");
                return;
        }

        servletRequest.getRequestDispatcher("/start-page").forward(req, res);
    }

    @Override
    public void destroy() {

    }
}
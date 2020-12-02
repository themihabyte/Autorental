package controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Main", urlPatterns = {"/Main"})
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
//        PrintWriter out = httpServletResponse.getWriter();
//        for (Automobile automobile: automobiles){
//            out.println(automobile);
//        }

        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/start-page");
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }
}

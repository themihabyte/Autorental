package controller;

import model.entity.Automobile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/catalogue-process")
public class CatalogueServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        List<String> automobiles= new ArrayList<>();
        automobiles.add("Mercedes");
        automobiles.add("Bugatti");
        httpServletRequest.setAttribute("automobiles", automobiles);
        getServletContext()
                .getRequestDispatcher("/catalogue")
                .forward(httpServletRequest, httpServletResponse);
    }
}

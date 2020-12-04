package controller;

import model.service.CatalogueService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/catalogue-process")
public class CatalogueServlet extends HttpServlet {
    CatalogueService catalogueService;
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        catalogueService = new CatalogueService();
        List<String> automobiles= catalogueService.getCatalogue();

        httpServletRequest.setAttribute("list", automobiles);
        getServletContext()
                .getRequestDispatcher("/catalogue-jsp")
                .forward(httpServletRequest, httpServletResponse);
    }
}

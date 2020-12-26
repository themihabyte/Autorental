package controller;

import model.entity.Automobile;
import model.service.CatalogueService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/catalogue-servlet")
public class CatalogueServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        CatalogueService catalogueService = new CatalogueService();
        List<Automobile> automobiles =null;
        Map<String, String[]> params = httpServletRequest.getParameterMap();

        try {
            automobiles = filterAutomobilesWithParameters(params, catalogueService);
        } catch (SQLException sqlException) {
            sendErrorBackToJSP(httpServletRequest, httpServletResponse, sqlException.getMessage());
        }
        sortAutomobilesWithParameter(params, catalogueService, automobiles);
        httpServletRequest.setAttribute("list", automobiles);

        List<String> manufacturers;
        try {
            manufacturers = catalogueService.getManufacturers();
        } catch (SQLException sqlException) {
            sendErrorBackToJSP(httpServletRequest, httpServletResponse,
                    sqlException.getMessage());
            return;
        }
        httpServletRequest.setAttribute("manufacturers", manufacturers);

        httpServletRequest.getRequestDispatcher("/catalogue-page")
                .forward(httpServletRequest, httpServletResponse);
    }

    private List<Automobile> filterAutomobilesWithParameters(Map<String, String[]> params, CatalogueService catalogueService) throws SQLException {
        List<Automobile> automobiles;
        if (params.containsKey("filter")) {
            Map<String, String> filters = new HashMap<>();
            filters.put("manufacturer", params.get("filter")[0]);
            filters.put("segment", params.get("filter")[1]);
            Set<Map.Entry<String, String>> entrySet = filters.entrySet();
            entrySet.removeIf(entry -> entry.getValue().equals("none"));
            if (!filters.isEmpty())
                automobiles = catalogueService.getAutomobilesFiltered(filters);
            else automobiles = catalogueService.getAllAutomobiles();
        } else {
            automobiles = catalogueService.getAllAutomobiles();
        }
        return automobiles;
    }
    private void sortAutomobilesWithParameter(Map<String, String[]> params, CatalogueService catalogueService, List<Automobile> automobiles){
        if (params.containsKey("sort")) {
            String sort = params.get("sort")[0];
            switch (sort) {
                case "value-asc":
                    automobiles = catalogueService.sortByValue(automobiles, false);
                    break;
                case "value-desc":
                    automobiles = catalogueService.sortByValue(automobiles, true);
                    break;
                case "alphabet":
                    automobiles = catalogueService.sortAlphabetically(automobiles);
                    break;
            }
        }
    }
    private void sendErrorBackToJSP(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    String errorMessage) throws ServletException, IOException {
        httpServletRequest.setAttribute("error_message", errorMessage);
        httpServletRequest.getRequestDispatcher("/catalogue-page").forward(httpServletRequest,
                httpServletResponse);
    }
}

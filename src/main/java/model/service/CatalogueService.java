package model.service;

import model.dataaccessunit.ConnectionPool;
import model.dataaccessunit.DAO;
import model.dataaccessunit.DAOFactory;
import model.entity.Automobile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueService{
    private static DAO<Automobile> automobileDAO;

    public CatalogueService() {
    }

    public static List<String> getCatalogue(){
        List<Automobile> automobiles= new ArrayList<>();
        try {
            automobileDAO = DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, ConnectionPool.getConnection());
        } catch (SQLException throwables) {
            //TODO exception
        }
        try {
            automobiles = automobileDAO.getAll();
        } catch (SQLException throwables) {
            //TODO
        }
        List<String> autoString = new ArrayList<>();
        for(Automobile automobile: automobiles){
            autoString.add(automobile.toString());
        }
        return autoString;
    }
}

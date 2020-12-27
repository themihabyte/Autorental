package model.service;

import model.dataaccessunit.AutomobileDAO;
import model.dataaccessunit.ConnectionPool;
import model.dataaccessunit.DAOFactory;
import model.entity.Automobile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CatalogueService {

    public CatalogueService() {
    }

    public List<Automobile> getAutomobilesFiltered(Map<String, String> filter) throws SQLException {
        List<Automobile> automobiles;
        try {
            AutomobileDAO automobileDAO = (AutomobileDAO) DAOFactory.getDAO(DAOFactory.Entities.AUTOMOBILE, ConnectionPool.getConnection());
            automobiles = automobileDAO.getAutomobilesFiltered(filter);
            ConnectionPool.closeConnection();
        } catch (SQLException throwables) {
            throw new SQLException("No connection to DB");
        }
        return automobiles;
    }

    public List<Automobile> sortAlphabetically(List<Automobile> automobiles) {
        automobiles.sort((o1, o2) -> {
            char[] chars1 = o1.getName().toCharArray();
            char[] chars2 = o2.getName().toCharArray();
            int length = Math.min(chars1.length, chars2.length);
            for (int i = 0; i < length; i++) {
                if (chars1[i] < chars2[i]) return 1;
                else if (chars1[i] > chars2[i]) return -1;
            }
            return 0;
        });
        return automobiles;
    }

    public List<Automobile> sortByValue(List<Automobile> automobiles, boolean descending) {
        Comparator<Automobile> automobileValueComparator = new Comparator<Automobile>() {

            @Override
            public int compare(Automobile o1, Automobile o2) {
                return Float.compare(o1.getPrice(), o2.getPrice());
            }
        };
        if (descending) automobiles.sort(automobileValueComparator.reversed());
        else automobiles.sort(automobileValueComparator);
        return automobiles;
    }
}

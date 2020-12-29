package model.service;

import model.dataaccessunit.AutomobileDAO;
import model.entity.Automobile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

class CatalogueServiceTest {
    private AutomobileDAO automobileDAO;
    private CatalogueService catalogueService;

    @BeforeEach
    void setUp() {
        try {
            catalogueService = new CatalogueService();
            automobileDAO = Mockito.mock(AutomobileDAO.class);
            Field privateField = catalogueService.getClass().getDeclaredField("automobileDAO");
            privateField.setAccessible(true);
            privateField.set(catalogueService, automobileDAO);
            privateField.setAccessible(false);
        } catch (SQLException | NoSuchFieldException | IllegalAccessException sqlException) {
            sqlException.printStackTrace();
        }
    }


    @Test
    void getAutomobilesFiltered() {
        Map<String, String> filters = new HashMap<>();
        filters.put("manufacturer", "Test");
        List<Automobile> automobiles = new ArrayList<>();

        try {
            automobiles.add(new Automobile(-1, Automobile.Segment.A,
                    "Test", "Test", 0.0f, false));
            Mockito.when(automobileDAO.getAutomobilesFiltered(filters)).thenReturn(
                    automobiles
            );
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        List<Automobile> automobiles1 = null;
        try {
            automobiles1= catalogueService.getAutomobilesFiltered(filters);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        Assertions.assertEquals(automobiles, automobiles1);
    }

    @Test
    void sortAlphabetically() {
        List<Automobile> automobiles = new ArrayList<>();
        automobiles.add(new Automobile(-1, Automobile.Segment.A,
                "AAA", "AAA", 0.0f, false));
        automobiles.add(new Automobile(-1, Automobile.Segment.A,
                "ABc", "ABc", 0.0f, false));
        automobiles.add(new Automobile(-1, Automobile.Segment.A,
                "ABa", "ABa", 0.0f, false));
        automobiles.add(new Automobile(-1, Automobile.Segment.A,
                "ABB", "ABB", 0.0f, false));
        automobiles= catalogueService.sortAlphabetically(automobiles);

        List<Automobile> automobiles1 = new ArrayList<>();
        automobiles1.add(new Automobile(-1, Automobile.Segment.A,
                "AAA", "AAA", 0.0f, false));
        automobiles1.add(new Automobile(-1, Automobile.Segment.A,
                "ABa", "ABa", 0.0f, false));
        automobiles1.add(new Automobile(-1, Automobile.Segment.A,
                "ABB", "ABB", 0.0f, false));
        automobiles1.add(new Automobile(-1, Automobile.Segment.A,
                "ABc", "ABc", 0.0f, false));
        Assertions.assertEquals(automobiles1, automobiles);
    }


    @Test
    void sortByValue() {
        List<Automobile> automobiles = new ArrayList<>();
        automobiles.add(new Automobile(-1, Automobile.Segment.A,
                "", "", 0.0f, false));
        automobiles.add(new Automobile(-1, Automobile.Segment.A,
                "", "", 2f, false));
        automobiles.add(new Automobile(-1, Automobile.Segment.A,
                "", "", 1f, false));
        automobiles.add(new Automobile(-1, Automobile.Segment.A,
                "", "", 3f, false));
        automobiles= catalogueService.sortByValue(automobiles, false);

        List<Automobile> automobiles1 = new ArrayList<>();
        automobiles1.add(new Automobile(-1, Automobile.Segment.A,
                "", "", 0f, false));
        automobiles1.add(new Automobile(-1, Automobile.Segment.A,
                "", "", 1f, false));
        automobiles1.add(new Automobile(-1, Automobile.Segment.A,
                "", "", 2f, false));
        automobiles1.add(new Automobile(-1, Automobile.Segment.A,
                "", "", 3f, false));
        Assertions.assertEquals(automobiles1, automobiles);
    }
}
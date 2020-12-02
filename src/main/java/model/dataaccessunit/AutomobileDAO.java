package model.dataaccessunit;

import model.entity.Automobile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//TODO
public class AutomobileDAO extends DAO<Automobile> {

    protected AutomobileDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Automobile> getAll() throws SQLException {
        List<Automobile> automobiles = new ArrayList<>();
        String sql = "SELECT * FROM automobiles";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Automobile automobile = new Automobile(Integer.parseInt(resultSet.getString("automobile_id")),
                    Automobile.Segment.S, resultSet.getString("name"),
                    resultSet.getString("manufacturer"),
                    Float.parseFloat(resultSet.getString("price")),
                    resultSet.getBoolean("is_in_stock"));
            automobiles.add(automobile);
        }
        return automobiles;
    }

    @Override
    public Automobile getEntityByID(long ID) {
        return null;
    }

    @Override
    public Automobile update(Automobile entity) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void create(Automobile entity) {

    }
}

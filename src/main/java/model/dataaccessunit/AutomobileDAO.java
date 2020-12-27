package model.dataaccessunit;

import model.entity.Automobile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AutomobileDAO extends DAO<Automobile> {

    protected AutomobileDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Automobile> getAll() throws SQLException {
        String SQL = "SELECT automobiles.automobile_id, name," +
                " price, is_in_stock, segment, manufacturer" +
                " FROM automobiles INNER JOIN automobiles_manufacturers" +
                " ON automobiles.automobile_id = automobiles_manufacturers.automobile_id";
        PreparedStatement statement = connection.prepareStatement(SQL);
        ResultSet resultSet = statement.executeQuery();
        List<Automobile> automobiles = createListFromResultSet(resultSet);
        close(statement);
        return automobiles;
    }

    @Override
    public Automobile getEntityByID(int ID) throws SQLException {
        String SQL = "SELECT name, price, is_in_stock, segment, manufacturer " +
                "FROM automobiles INNER JOIN automobiles_manufacturers" +
                " ON automobiles.automobile_id = automobiles_manufacturers.automobile_id " +
                "WHERE automobiles.automobile_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Automobile automobile = new Automobile(ID,
                Automobile.Segment.valueOf(resultSet.getString("segment")),
                resultSet.getString("name"),
                resultSet.getString("manufacturer"),
                resultSet.getFloat("price"),
                resultSet.getBoolean("is_in_stock"));
        close(preparedStatement);
        return automobile;
    }

    public List<String> getManufacturers() throws SQLException {
        List<String> manufacturers = new ArrayList<>();
        String SQL = "SELECT * FROM manufacturers";
        PreparedStatement statement = connection.prepareStatement(SQL);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            manufacturers.add(resultSet.getString("manufacturer"));
        }
        close(statement);
        return manufacturers;
    }

    @Override
    public void update(Automobile entity) throws SQLException {
        String SQL = "UPDATE automobiles SET name = " + "'" + entity.getName() + "'" + "," +
                " price = " + entity.getPrice() + ", is_in_stock = " + entity.isInStock()
                + ", segment = " + "'" + entity.getSegment() + "'" +
                " WHERE automobile_id = " + entity.getId();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.addBatch(SQL);

        SQL = "INSERT INTO manufacturers (manufacturer)" +
                " VALUES ('" + entity.getManufacturer() + "')" +
                " ON CONFLICT (manufacturer) DO NOTHING";
        statement.addBatch(SQL);

        SQL = "UPDATE automobiles_manufacturers" +
                " SET manufacturer = " + "'" + entity.getManufacturer() + "'" +
                " WHERE automobile_id = " + entity.getId();
        statement.addBatch(SQL);
        statement.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
        close(statement);
    }

    @Override
    public void delete(int id) throws SQLException {
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        String SQL = "DELETE FROM automobiles_manufacturers" +
                " WHERE automobile_id = " + id;
        statement.addBatch(SQL);

        SQL = "DELETE FROM automobiles" +
                " WHERE automobile_id = " + id;
        statement.addBatch(SQL);
        try {
            statement.executeBatch();
        } catch (SQLException sqlException){
            connection.rollback();
            throw new SQLException("You can`t delete this automobile," +
                    " it`s in use");
        }
        connection.commit();
        connection.setAutoCommit(true);
        close(statement);
    }

    @Override
    public int create(Automobile entity) throws SQLException {
        String SQL = "INSERT INTO automobiles (name, price, " +
                "is_in_stock, segment) VALUES ('" +
                entity.getName() + "', " +
                entity.getPrice() + ", " + entity.isInStock() +
                ", " + "'" + entity.getSegment() + "')";
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS);

        try {
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            connection.rollback();
            throw sqlException;
        }
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        entity.setId(resultSet.getInt(1));
        close(statement);

        Statement statement1 = connection.createStatement();
        SQL = "INSERT INTO manufacturers (manufacturer)" +
                " VALUES ('" + entity.getManufacturer() + "')" +
                " ON CONFLICT (manufacturer) DO NOTHING";
        statement1.addBatch(SQL);


        SQL = "INSERT INTO automobiles_manufacturers(automobile_id, manufacturer)" +
                " VALUES(" + entity.getId() + ", '" + entity.getManufacturer() + "')";
        statement1.addBatch(SQL);
        try {
            statement1.executeBatch();
        } catch (SQLException sqlException) {
            connection.rollback();
            throw sqlException;
        }
        connection.commit();
        connection.setAutoCommit(true);
        close(statement1);
        return entity.getId();
    }

    public List<Automobile> getAutomobilesFiltered(Map<String, String> filter) throws SQLException {

        StringBuilder SQL = new StringBuilder("SELECT automobiles.automobile_id, name, price, is_in_stock, segment,manufacturer" +
                " FROM automobiles INNER JOIN automobiles_manufacturers" +
                " ON automobiles.automobile_id = automobiles_manufacturers.automobile_id" +
                " WHERE ");
        int iterationsCounter = 0;
        for (Map.Entry<String, String> stringEntry : filter.entrySet()) {
            if (iterationsCounter != 0) SQL.append(" AND ");
            SQL.append(stringEntry.getKey()).append(" = '").append(stringEntry.getValue()).append("'");
            iterationsCounter++;
        }
        PreparedStatement preparedStatement = connection.prepareStatement(SQL.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Automobile> automobiles = createListFromResultSet(resultSet);
        close(preparedStatement);
        return automobiles;
    }

    private List<Automobile> createListFromResultSet(ResultSet resultSet) throws SQLException {
        List<Automobile> automobiles = new ArrayList<>();
        while (resultSet.next()) {
            Automobile automobile = new Automobile(resultSet.getInt("automobile_id"),
                    Automobile.Segment.valueOf(resultSet.getString("segment")),
                    resultSet.getString("name"),
                    resultSet.getString("manufacturer"),
                    resultSet.getFloat("price"),
                    resultSet.getBoolean("is_in_stock"));
            automobiles.add(automobile);
        }
        return automobiles;
    }
}

package model.dataaccessunit;

import model.entity.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO extends DAO<Bill>{

    protected BillDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Bill> getAll() throws SQLException {
        List<Bill> bills = new ArrayList<>();
        String SQL = "SELECT * FROM bills";
        PreparedStatement statement = connection.prepareStatement(SQL);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Bill bill = new Bill(resultSet.getInt("order_id"),
                    resultSet.getFloat("price"),
                    resultSet.getBoolean("is_payed"));
            bills.add(bill);
        }
        close(statement);
        return bills;
    }

    @Override
    public Bill getEntityByID(int ID) throws SQLException {
        String SQL = "SELECT * FROM bills " +
                "WHERE order_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        close(preparedStatement);

        return new Bill(resultSet.getInt("order_id"),
                resultSet.getFloat("price"),
                resultSet.getBoolean("is_payed"));
    }

    @Override
    public void update(Bill entity) throws SQLException {
        String SQL = "UPDATE bills" +
                " SET is_payed = ?,"+
                " price = ?" +
                " WHERE order_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setBoolean(1, entity.isPayed());
        preparedStatement.setFloat(2, entity.getPrice());
        preparedStatement.setInt(3, entity.getOrderId());
        preparedStatement.executeUpdate();
        close(preparedStatement);
    }

    @Override
    public void delete(int id) throws SQLException {
        String SQL = "DELETE FROM bills WHERE order_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        close(preparedStatement);
    }

    @Override
    public int create(Bill entity) throws SQLException {
        String SQL = "INSERT INTO bills (order_id, price, is_payed)"+
                " VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, entity.getOrderId());
        statement.setFloat(2, entity.getPrice());
        statement.setBoolean(3, entity.isPayed());
        statement.executeUpdate();
        int id = -1;
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        close(statement);
        return id;
    }
}

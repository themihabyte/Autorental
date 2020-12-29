package model.dataaccessunit;

import model.entity.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DAO<Order>{

    protected OrderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Order> getAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Order order = new Order(resultSet.getInt("order_id"),
                    resultSet.getInt("user_id"),
                    resultSet.getInt("automobile_id"),
                    resultSet.getString("passport_details"),
                    resultSet.getString("start_date"),
                    resultSet.getString("end_date"),
                    resultSet.getBoolean("has_driver"),
                    resultSet.getBoolean("is_denied"));
            if (order.isDenied()){
                order.setRejectionReason(resultSet.getString("rejection_reason"));
            }
            orders.add(order);
        }
        close(statement);
        return orders;
    }

    @Override
    public Order getEntityByID(int ID) throws SQLException {
        String SQL = "SELECT * FROM orders " +
                "WHERE order_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        close(preparedStatement);
        Order order = new Order(resultSet.getInt("order_id"),
                resultSet.getInt("user_id"),
                resultSet.getInt("automobile_id"),
                resultSet.getString("passport_details"),
                resultSet.getString("start_date"),
                resultSet.getString("end_date"),
                resultSet.getBoolean("has_driver"),
                resultSet.getBoolean("is_denied"));
        if (order.isDenied()){
            order.setRejectionReason(resultSet.getString("rejection_reason"));
        }
        return order;
    }

    @Override
    public void update(Order entity) throws SQLException {
        String SQL = "UPDATE orders" +
                " SET user_id = ?," +
                " automobile_id = ?," +
                " passport_details = ?," +
                " start_date = ?," +
                " end_date = ?," +
                " has_driver = ?," +
                " is_denied = ?," +
                " rejection_reason = ?" +
                " WHERE order_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, entity.getUserID());
        preparedStatement.setInt(2, entity.getAutomobileID());
        preparedStatement.setString(3, entity.getPassportDetails());
        preparedStatement.setDate(4, Date.valueOf(entity.getStartDate()));
        preparedStatement.setDate(5, Date.valueOf(entity.getEndDate()));
        preparedStatement.setBoolean(6, entity.isHasDriver());
        preparedStatement.setBoolean(7, entity.isDenied());
        preparedStatement.setString(8, entity.getRejectionReason());
        preparedStatement.setInt(9, entity.getId());
        preparedStatement.executeUpdate();
        close(preparedStatement);
    }

    @Override
    public void delete(int id) throws SQLException {
        String SQL = "DELETE FROM orders WHERE order_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        close(preparedStatement);
    }

    public List<Order> getOrdersByUserID(int id) throws SQLException{
        String SQL = "SELECT * from orders WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()){
            Order order = new Order(resultSet.getInt("order_id"),
                    resultSet.getInt("user_id"),
                    resultSet.getInt("automobile_id"),
                    resultSet.getString("passport_details"),
                    resultSet.getString("start_date"),
                    resultSet.getString("end_date"),
                    resultSet.getBoolean("has_driver"),
                    resultSet.getBoolean("is_denied"));
            if (order.isDenied()){
                order.setRejectionReason(resultSet.getString("rejection_reason"));
            }
            orders.add(order);
        }
        return orders;
    }

    @Override
    public int create(Order entity) throws SQLException {
        String SQL = "INSERT INTO orders (user_id, automobile_id," +
                " passport_details, start_date, end_date," +
                " has_driver, is_denied)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, entity.getUserID());
        statement.setInt(2, entity.getAutomobileID());
        statement.setString(3, entity.getPassportDetails());
        statement.setDate(4, Date.valueOf(entity.getStartDate()));
        statement.setDate(5, Date.valueOf(entity.getEndDate()));
        statement.setBoolean(6, entity.isHasDriver());
        statement.setBoolean(7, entity.isDenied());
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

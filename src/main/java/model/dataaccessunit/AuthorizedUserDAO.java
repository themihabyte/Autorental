package model.dataaccessunit;

import model.entity.Bill;
import model.entity.User;
import model.service.UserServiceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorizedUserDAO extends DAO<User> {


    protected AuthorizedUserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            User user = new User(resultSet.getInt("user_id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    UserServiceFactory.UserRole.valueOf(
                            resultSet.getString("role")
                    ), resultSet.getBoolean("is_banned"));
            users.add(user);
        }
        close(statement);
        return users;
    }

    @Override
    public User getEntityByID(int ID) throws SQLException {
        String SQL = "SELECT * FROM users " +
                "WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        close(preparedStatement);

        return new User(ID, resultSet.getString("username"),
                resultSet.getString("password"),
                UserServiceFactory.UserRole.valueOf(
                        resultSet.getString("role")
                ), resultSet.getBoolean("is_banned"));
    }

    @Override
    public void update(User entity) throws SQLException {
        String SQL = "UPDATE users" +
                " SET username = ?"+
                " password = ?" +
                " role = ?" +
                " is_banned = ?" +
                " WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, entity.getUsername());
        preparedStatement.setString(2, String.valueOf(entity.getRole()));
        preparedStatement.setBoolean(3, entity.isBanned());
        preparedStatement.setInt(4, entity.getId());
        preparedStatement.executeUpdate();
        close(preparedStatement);
    }

    @Override
    public void delete(int id) throws SQLException {
        String SQL = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        close(preparedStatement);
    }

    @Override
    public int create(User entity) throws SQLException {
        String SQL = "INSERT INTO users(username, password," +
                "role, is_banned) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, entity.getUsername());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, String.valueOf(entity.getRole()));
        preparedStatement.setBoolean(4, entity.isBanned());
        preparedStatement.executeUpdate();
        int id = -1;
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        close(preparedStatement);
        return id;
    }

    public User findByUsernamePassword(String username, String password) throws SQLException {
        String SQL = "SELECT user_id, username, password, role, is_banned " +
                "FROM users " +
                "WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        close(preparedStatement);
        return new User(resultSet.getInt("user_id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                UserServiceFactory.UserRole.valueOf(resultSet.getString("role")),
                resultSet.getBoolean("is_banned"));
    }
}

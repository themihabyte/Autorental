package model.dataaccessunit;

import model.entity.User;
import model.service.UserServiceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//TODO
public class AuthorizedUserDAO extends DAO<User> {


    protected AuthorizedUserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getEntityByID(long ID) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void create(User entity) throws SQLException {
        String SQL = "INSERT INTO users(username, password," +
                "role, is_banned) " +
                "VALUES(" + entity.getUsername() + "," +
                entity.getPassword() + "," +
                entity.getRole() + "," +
                entity.isBanned() + ")";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.executeUpdate();
    }

    public User findByUsernamePassword(String username, String password) throws SQLException {
        String SQL = "SELECT user_id, username, password, role, is_banned " +
                "FROM users " +
                "WHERE username = " + "'"+username+"'" + " AND " +
                "password = " + "'"+password+"'";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        User user =new User(resultSet.getInt("user_id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                UserServiceFactory.UserRole.valueOf(resultSet.getString("role")),
                resultSet.getBoolean("is_banned"));
        return user;
    }
}

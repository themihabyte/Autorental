package model.dataaccessunit;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DAO<T> {
    protected Connection connection;

    protected DAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> getAll() throws SQLException;
    public abstract T getEntityByID(int ID) throws SQLException;
    public abstract void update(T entity) throws SQLException;
    public abstract void delete(int id) throws SQLException;
    public abstract int create(T entity) throws SQLException;

    public void close(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }
}

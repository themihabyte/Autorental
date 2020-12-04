package model.dataaccessunit;

import org.postgresql.ds.PGConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public abstract class DAO<T> {
    protected Connection connection;

    protected DAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> getAll() throws SQLException;
    public abstract T getEntityByID(long ID);
    public abstract T update(T entity);
    public abstract void delete(long id);
    public abstract void create(T entity) throws SQLException;

    public void close(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }
}

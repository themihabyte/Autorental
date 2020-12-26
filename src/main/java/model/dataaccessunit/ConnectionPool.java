package model.dataaccessunit;

import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {
    private ConnectionPool() {
    }

    private static DataSource dataSource;
    private static Connection connection;
    private static DataSource getDataSource(){
        if (dataSource == null){
            synchronized (ConnectionPool.class){
                if (dataSource == null){
                    Jdbc3PoolingDataSource ds = new Jdbc3PoolingDataSource();
                    ResourceBundle bundle = ResourceBundle.getBundle("database");
                    ds.setDataSourceName(bundle.getString("dataSourceName"));
                    ds.setURL(bundle.getString("url"));
                    ds.setUser(bundle.getString("user"));
                    ds.setPassword(bundle.getString("password"));

                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        connection = getDataSource().getConnection();
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}

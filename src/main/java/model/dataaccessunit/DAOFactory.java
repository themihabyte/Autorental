package model.dataaccessunit;

import java.sql.Connection;

public class DAOFactory {
    public enum Entities{USER, AUTOMOBILE, ORDER, BILL}
    public static DAO getDAO(Entities entity, Connection connection) {
        switch (entity){
            case BILL: return new BillDAO(connection);
            case USER: return new AuthorizedUserDAO(connection);
            case ORDER: return new OrderDAO(connection);
            case AUTOMOBILE: return new AutomobileDAO(connection);
        }
        return null;
    }
}

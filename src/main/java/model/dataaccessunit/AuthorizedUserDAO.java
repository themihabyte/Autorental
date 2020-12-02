package model.dataaccessunit;

import model.entity.User;

import java.sql.Connection;
import java.util.List;
//TODO
public class AuthorizedUserDAO extends DAO<User>{


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
    public void create(User entity) {

    }
}

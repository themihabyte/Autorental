package model.dataaccessunit;

import model.entity.Bill;

import java.sql.Connection;
import java.util.List;
//TODO
public class BillDAO extends DAO<Bill>{

    protected BillDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Bill> getAll() {
        return null;
    }

    @Override
    public Bill getEntityByID(long ID) {
        return null;
    }

    @Override
    public Bill update(Bill entity) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void create(Bill entity) {

    }
}

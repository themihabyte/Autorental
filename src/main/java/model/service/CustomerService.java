package model.service;

import model.entity.Bill;
import model.entity.Order;
import model.entity.User;

import java.util.Date;
//TODO
public class CustomerService extends AuthorizedUserService {

    protected CustomerService(String username, String password, User.Role role) {
        super(username, password, role);
    }

    public Order makeOrder(String passportDetails, Date startDate, Date endDate, boolean isHasDriver, int automobile){
        return new Order(this.user.getId(), passportDetails, startDate, endDate, isHasDriver, new Bill(), automobile);
    }

    public void payBill(Bill bill){

    }
}

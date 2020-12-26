package model.service;
import model.entity.Automobile;
import model.entity.User;

public class UserAdministratorService extends AuthorizedUserService {

    protected UserAdministratorService(User user) {
        super(user);
    }

    public void addAutomobile(Automobile.Segment segment, String name, String manufacturer, float price, boolean isInStock){
    }
    public void updateAutomobile(long id, Automobile.Segment segment, String name, String manufacturer, float price, boolean isInStock){

    }

    public Automobile deleteAutomobile(long automobileID){
        return null;
    }

    public void banCustomer(long customerID){

    }

    public User registerManager(String username, String password, boolean isBanned){
        return null;
    }
}

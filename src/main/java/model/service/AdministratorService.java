package model.service;
import model.entity.Automobile;
import model.entity.User;
//TODO
public class AdministratorService extends AuthorizedUserService{

    protected AdministratorService(String username, String password, User.Role role) {
        super(username, password, role);
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

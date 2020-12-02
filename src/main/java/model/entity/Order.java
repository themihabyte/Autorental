package model.entity;

import java.util.Date;

public class Order {
    private int id;
    private int userID;
    private int automobileID;
    private String passportDetails;
    private Date startDate;
    private Date endDate;
    private boolean isHasDriver;
    private boolean isDenied;

    public Order(int userID, String passportDetails, Date startDate, Date endDate, boolean isHasDriver, Bill bill, int automobileID) {
        this.userID = userID;
        this.passportDetails = passportDetails;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isHasDriver = isHasDriver;
        this.automobileID = automobileID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPassportDetails() {
        return passportDetails;
    }

    public void setPassportDetails(String passportDetails) {
        this.passportDetails = passportDetails;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isHasDriver() {
        return isHasDriver;
    }

    public void setHasDriver(boolean hasDriver) {
        this.isHasDriver = hasDriver;
    }

    public boolean isDenied() {
        return isDenied;
    }

    public void setDenied(boolean denied) {
        isDenied = denied;
    }

    public int getAutomobileID() {
        return automobileID;
    }

    public void setAutomobileID(int automobileID) {
        this.automobileID = automobileID;
    }
}

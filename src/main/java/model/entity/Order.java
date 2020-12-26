package model.entity;


public class Order {
    private int id;
    private int userID;
    private int automobileID;
    private String passportDetails;
    private String startDate;
    private String endDate;
    private Bill bill;
    private Automobile automobile;
    private String rejectionReason;

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    private boolean isHasDriver;
    private boolean isDenied;

    public Order(int id, int userID, int automobileID, String passportDetails, String startDate, String endDate, boolean isHasDriver, boolean isDenied) {
        this.id = id;
        this.userID = userID;
        this.automobileID = automobileID;
        this.passportDetails = passportDetails;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isHasDriver = isHasDriver;
        this.isDenied = isDenied;
    }

    public Order(int userID, int automobileID, String passportDetails, String startDate, String endDate, boolean isHasDriver, boolean isDenied) {
        this.userID = userID;
        this.passportDetails = passportDetails;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isHasDriver = isHasDriver;
        this.isDenied = isDenied;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

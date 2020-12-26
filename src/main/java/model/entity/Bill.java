package model.entity;

public class Bill {
    private int orderId;
    private float price;
    private boolean isPayed;

    public Bill(int orderId, float price, boolean isPayed) {
        this.orderId = orderId;
        this.price = price;
        this.isPayed = isPayed;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }
}

package model.entity;

import java.util.List;

public class Automobile {
    public enum Segment {
        A, B, C, D, E, F, J, M, S
    }
    private int id;
    private Segment segment;
    private String name;
    private String manufacturer;
    private float price;
    private boolean isInStock;

    public Automobile(Segment segment, String name, String manufacturer, float price, boolean isInStock) {
        this.segment = segment;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.isInStock = isInStock;
    }

    public Automobile(int id, Segment segment, String name, String manufacturer, float price, boolean isInStock) {
        this.id = id;
        this.segment = segment;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.isInStock = isInStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isInStock() {
        return isInStock;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "id=" + id +
                ", segment=" + segment +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", isInStock=" + isInStock +
                '}';
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }
}

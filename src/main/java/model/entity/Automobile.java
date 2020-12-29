package model.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automobile that = (Automobile) o;
        return Float.compare(that.price, price) == 0 && segment == that.segment && name.equals(that.name) && manufacturer.equals(that.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segment, name, manufacturer, price);
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

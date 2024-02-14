package pdp.bean;

import java.awt.*;

public class Car {
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String color;
    private double price;
    private Integer userId;
    private boolean isInStore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isInStore() {
        return isInStore;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", userId=" + userId +
                ", isInStore=" + isInStore +
                '}';
    }

    public Car() {
    }

    public Car(String name, String color, double price, Integer userId, boolean isInStore) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.userId = userId;
        this.isInStore = isInStore;
    }

    public void setInStore(boolean inStore) {
        isInStore = inStore;
    }


}

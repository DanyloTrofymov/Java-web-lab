package org.example.entities.good;

import java.util.Objects;

public class Good {
    private int id;

    private String name;

    private GoodType type;

    private float price;

    public Good(int id, String name, GoodType type, float price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Good(String name, GoodType type, float price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Good(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.type = GoodType.OTHER;
        this.price = price;
    }

    public Good(String name, float price) {
        this.name = name;
        this.type = GoodType.OTHER;
        this.price = price;
    }

    public Good() {};

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GoodType getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(GoodType type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = GoodType.getType(type);
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return id == good.id && Float.compare(good.price, price) == 0 && Objects.equals(name, good.name) && type == good.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, price);
    }
}

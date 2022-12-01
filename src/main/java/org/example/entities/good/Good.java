package org.example.entities.good;

import java.util.Objects;
import java.util.UUID;

public class Good {
    private String id;

    private String name;

    private GoodType type;

    private float price;

    private float amount;

    public Good(String id, String name, GoodType type, float price, float amount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.amount = amount;
    }

    public Good(String name, GoodType type, float price, float onStorage) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.price = price;
        this.amount = onStorage;
    }

    public Good(String id, String name, float price, float onStorage) {
        this.id = id;
        this.name = name;
        this.type = GoodType.OTHER;
        this.price = price;
        this.amount = onStorage;
    }

    public Good(String name, float price, float onStorage) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = GoodType.OTHER;
        this.price = price;
        this.amount = onStorage;
    }

    public Good() {};

    public String getId() {
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

    public float getAmount() { return amount; }

    public void setId(String id) {
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

    public void setAmount(float amount) { this.amount = amount; }

    public boolean isNull(){
        return (id == null && name == null && type == null && price == 0 && amount == 0);
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return id == good.id && Float.compare(good.price, price) == 0 && Float.compare(good.amount, amount) == 0 && name.equals(good.name) && type == good.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, price, amount);
    }
}

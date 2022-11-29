package org.example.entities.good;

public class Good {
    private int id;
    private String name;
    private GoodType type;
    private float price;
    public Good(){};

    public Good(String name, GoodType type, float price) {
        this.name = name;
        if (type != null) {
            this.type = type;
        }
        else {
            this.type = GoodType.OTHER;
        }
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
}

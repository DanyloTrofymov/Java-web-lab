package org.example.entities.good;

public class Good {
    private String id;
    private String name;
    private Type type;
    private float price;

    public Good(String id, String name, Type type, float price) {
        this.id = id;
        this.name = name;
        if (type != null) {
            this.type = type;
        }
        else {
            this.type = Type.OTHER;
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }
}

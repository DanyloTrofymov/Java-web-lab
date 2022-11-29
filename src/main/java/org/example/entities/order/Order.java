package org.example.entities.order;

import java.util.Objects;

public class Order {
    private int id;

    private String buyerName;

    private OrderStatus status;

    private float totalPrice;

    public Order(int id, String buyerName, float totalPrice) {
        this.id = id;
        this.buyerName = buyerName;
        this.status = OrderStatus.REGISTERED;
        this.totalPrice = totalPrice;
    }

    public Order(String buyerName, float totalPrice) {
        this.buyerName = buyerName;
        this.status = OrderStatus.REGISTERED;
        this.totalPrice = totalPrice;
    }

    public Order(int id, String buyerName, OrderStatus status, float totalPrice) {
        this.id = id;
        this.buyerName = buyerName;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Order(String buyerName, OrderStatus status, float totalPrice) {
        this.buyerName = buyerName;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Order() {};

    public int getId() { return id; }

    public String getBuyerName() { return buyerName; }

    public OrderStatus getStatus() { return status; }

    public float getTotalPrice() { return totalPrice; }

    public void setId(int id) { this.id = id; }

    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }

    public void setTotalPrice(float totalPrice) { this.totalPrice = totalPrice; }

    public void setStatus(OrderStatus status) { this.status = status; }

    public void setStatus(String status) { this.status = OrderStatus.getStatus(status); }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Float.compare(order.totalPrice, totalPrice) == 0 && Objects.equals(buyerName, order.buyerName) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyerName, status, totalPrice);
    }
}

package org.example.entities.order;

public class Order {
    private String id;
    private String buyerName;
    private OrderStatus status;
    private float totalPrice;

    public Order(String id, String buyerName, float totalPrice) {
        this.id = id;
        this.buyerName = buyerName;
        this.status = OrderStatus.REGISTERED;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}

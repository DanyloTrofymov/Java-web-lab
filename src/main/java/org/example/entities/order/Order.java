package org.example.entities.order;

import org.example.entities.good.Good;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private String id;

    private String buyerName;

    private OrderStatus status;

    private List<Good> goods;

    private float totalPrice;

    public Order(String id, String buyerName, float totalPrice, List<Good> goods) {
        this.id = id;
        this.buyerName = buyerName;
        this.status = OrderStatus.REGISTERED;
        this.totalPrice = totalPrice;
        this.goods = goods;
    }

    public Order(String buyerName, float totalPrice, List<Good> goods) {
        this.id = UUID.randomUUID().toString();
        this.buyerName = buyerName;
        this.status = OrderStatus.REGISTERED;
        this.totalPrice = totalPrice;
        this.goods = goods;
    }

    public Order(String id, String buyerName, OrderStatus status, float totalPrice, List<Good> goods) {
        this.id = id;
        this.buyerName = buyerName;
        this.status = status;
        this.totalPrice = totalPrice;
        this.goods = goods;
    }

    public Order(String buyerName, OrderStatus status, float totalPrice, List<Good> goods) {
        this.id = UUID.randomUUID().toString();
        this.buyerName = buyerName;
        this.status = status;
        this.totalPrice = totalPrice;
        this.goods = goods;
    }

    public Order() {};

    public String getId() { return id; }

    public String getBuyerName() { return buyerName; }

    public OrderStatus getStatus() { return status; }

    public float getTotalPrice() { return totalPrice; }

    public List<Good> getGoods() { return goods; }

    public void setId(String id) { this.id = id; }

    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }

    public void setTotalPrice(float totalPrice) { this.totalPrice = totalPrice; }

    public void setStatus(OrderStatus status) { this.status = status; }

    public void setStatus(String status) { this.status = OrderStatus.getStatus(status); }

    public void setGoods(List<Good> goods) { this.goods = goods; }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", buyerName='" + buyerName + '\'' +
                ", status=" + status +
                ", goods=" + goods +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Float.compare(order.totalPrice, totalPrice) == 0 && buyerName.equals(order.buyerName) && status == order.status && goods.equals(order.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyerName, status, goods, totalPrice);
    }
}

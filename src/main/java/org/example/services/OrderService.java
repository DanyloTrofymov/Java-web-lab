package org.example.services;

import org.example.entities.order.Order;
import org.example.entities.order.OrderStatus;
import org.example.repositories.dao.AbstractOrderService;

import java.util.List;

public class OrderService {
    private final AbstractOrderService orderService;

    public OrderService(AbstractOrderService service) { this.orderService = service; }

    public void create(Order order) { orderService.create(order); }

    public List<Order> findAll() { return orderService.findAll(); }

    public Order findById(int id) { return orderService.findById(id); }

    public void update(int id, Order order) { orderService.update(id, order); }

    public void delete(int id) { orderService.delete(id); }

    public List<Order> findByStatus(OrderStatus order) { return orderService.findByStatus(order); }

}


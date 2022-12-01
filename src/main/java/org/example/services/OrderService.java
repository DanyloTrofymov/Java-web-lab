package org.example.services;

import org.example.entities.order.Order;
import org.example.entities.order.OrderStatus;
import org.example.repositories.dao.AbstractDaoOrderService;

import java.util.List;

public class OrderService {
    private final AbstractDaoOrderService orderService;

    public OrderService(AbstractDaoOrderService orderService) { this.orderService = orderService; }

    public void create(Order order) { orderService.create(order); }

    public List<Order> findAll() { return orderService.findAll(); }

    public Order findById(String id) { return orderService.findById(id); }

    public void update(String id, Order order) { orderService.update(id, order); }

    public void delete(String id) { orderService.delete(id); }

    public List<Order> findByStatus(OrderStatus order) { return orderService.findByStatus(order); }

}


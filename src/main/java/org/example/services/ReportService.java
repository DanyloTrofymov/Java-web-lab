package org.example.services;

import org.example.entities.good.Good;
import org.example.entities.good.GoodType;
import org.example.entities.order.Order;
import org.example.repositories.dao.AbstractDaoGoodService;
import org.example.repositories.dao.AbstractDaoOrderService;

import java.util.*;
import java.util.function.BinaryOperator;

public class ReportService {

    private final AbstractDaoOrderService orderService;

    private final AbstractDaoGoodService goodService;

    public ReportService(AbstractDaoOrderService orderService, AbstractDaoGoodService goodService) {
        this.orderService = orderService;
        this.goodService = goodService;
    }

    public float getSum(){
        List<Order> orders = orderService.findAll();
        float sum = 0;
        for (Order order: orders) {
            sum += order.getTotalPrice();
        }
        return sum;
    }
    public float getAvg(){
        List<Order> orders = orderService.findAll();
        float sum = getSum();
        int count = orders.size();
        return sum / count;
    }
    public int getCount(){
        return orderService.findAll().size();
    }

    public GoodType getMostPopular(){
        List<GoodType> types = getAllGoodTypes();

        return types.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(o -> Collections.frequency(types, o)))).orElse(null);
    }

    public List<GoodType> getAllGoodTypes(){
        List<Good> goods = goodService.findAll();
        List<GoodType> types = new ArrayList<>();
        for (Good good : goods) {
            types.add(good.getType());
        }
        return types;
    }
}

package org.example.services;

import org.example.entities.good.Good;
import org.example.entities.good.GoodType;
import org.example.entities.order.Order;
import org.example.repositories.dao.AbstractGoodService;
import org.example.repositories.dao.AbstractOrderService;
import org.example.views.report.ReportView;

import java.util.*;
import java.util.function.BinaryOperator;

public class ReportService {

    private ReportView view;

    private final AbstractOrderService orderService;

    private final AbstractGoodService goodService;

    public ReportService(AbstractOrderService orderService, AbstractGoodService goodService, ReportView view) {
        this.orderService = orderService;
        this.goodService = goodService;
        this.view = view;
    }

    public void report_X(){
        List<Order> orders = orderService.findAll();
        float sum = 0;
        for (Order order: orders) {
            sum += order.getTotalPrice();
        }
        int count = orders.size();
        float avg = sum / count;

        view.printXReport(count, avg, sum);
    }

    public void report_Y(){
        List<Good> goods = goodService.findAll();
        List<GoodType> types = new ArrayList<>();
        for (Good good : goods) {
            types.add(good.getType());
        }

        Set<GoodType> countOfTypes = new HashSet<>(types);

        GoodType maxOccurredElement = types.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(o -> Collections.frequency(types, o)))).orElse(null);

        view.printYReport(countOfTypes, maxOccurredElement);

    }
}

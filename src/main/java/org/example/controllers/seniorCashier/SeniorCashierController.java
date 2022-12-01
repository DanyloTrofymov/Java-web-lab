package org.example.controllers.seniorCashier;

import org.example.controllers.cashier.CashierController;
import org.example.entities.good.Good;
import org.example.entities.good.GoodType;
import org.example.entities.order.Order;
import org.example.exceptions.DatabaseException;
import org.example.services.GoodService;
import org.example.services.OrderService;
import org.example.services.ReportService;
import org.example.views.seniorCashier.SeniorCashierView;

import java.io.IOError;
import java.util.List;
import java.util.Set;

public class SeniorCashierController extends CashierController {
    SeniorCashierView seniorCashierView;
    ReportService reportService;
    public SeniorCashierController(SeniorCashierView seniorCashierView, OrderService orderService, GoodService goodService, ReportService reportService) {
        super(seniorCashierView, orderService, goodService);
        this.reportService = reportService;
        this.seniorCashierView = seniorCashierView;
    }

    @Override
    public void editOrder() {
        try {
            String action = seniorCashierView.chooseActionWithGood();
            if (action.equals("edit")) {
                super.editOrder();
            }
            if (action.equals("remove")) {
                Order order = findOrder();
                boolean editGoods = true;
                while (editGoods) {
                    String name = seniorCashierView.getGoodName();
                    List<Good> goods = order.getGoods();
                    Good searched = null;
                    for (Good good : goods) {
                        if (good.getName().equals(name)) {
                            searched = good;
                            goods.remove(good);
                        }
                    }
                    if (searched == null) {
                        seniorCashierView.nameNotFound(name);
                        continue;
                    }
                    order.setGoods(goods);
                    editGoods = seniorCashierView.wantToContinue();
                }
                orderService.update(order.getId(), order);
            }
        } catch (DatabaseException e) {
            System.out.println("Internal server error. ");
        } catch (IOError e) {
            System.out.println("Input error");
        }
    }

    public void getReport(){
        try {
            String type = seniorCashierView.chooseReportType();
            if(type.equals("x")) {
                int count = reportService.getCount();
                float avg = reportService.getAvg();
                float sum = reportService.getSum();
                seniorCashierView.printXReport(count, avg, sum);
            }
            if (type.equals("z")){
                Set<GoodType> countOfTypes = reportService.getCountOfTypes();
                GoodType mostPopular = reportService.getMostPopular();
                seniorCashierView.printZReport(countOfTypes, mostPopular);
            }

        } catch (DatabaseException e) {
            System.out.println("Internal server error. ");
        }
    }

}


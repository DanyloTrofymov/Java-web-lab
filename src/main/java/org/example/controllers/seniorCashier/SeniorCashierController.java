package org.example.controllers.seniorCashier;

import org.example.controllers.cashier.CashierAction;
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
import java.util.HashSet;
import java.util.InputMismatchException;
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
    public void start(){
        boolean login = true;
        while (login){
            SeniorCashierAction action = seniorCashierView.chooseSeniorAction();
            switch (action){
                case CREATE_ORDER -> createOrder();
                case EDIT_ORDER -> editOrder();
                case EDIT_STATUS -> editStatus();
                case FIND_ALL -> findAll();
                case FIND_BY_STATUS -> findByStatus();
                case GET_REPORT -> getReport();
                case LOGOUT -> login = false;
            }
        }
    }
    @Override
    protected void editOrder() {
        try {
            String action = seniorCashierView.chooseActionWithGood();
            if (action.equals("edit")) {
                super.editOrder();
            }
            if (action.equals("remove")) {
                Order order = findOrder();
                seniorCashierView.print(order.toString());
                if(order.isNull()){
                    return;
                }
                boolean editGoods = true;
                while (editGoods) {
                    String name = seniorCashierView.getGoodName();
                    List<Good> goods = order.getGoods();
                    Good searched = null;
                    for (Good good : goods) {
                        if (good.getName().equals(name)) {
                            searched = good;
                            goods.remove(good);
                            break;
                        }
                    }
                    if (searched == null) {
                        seniorCashierView.nameNotFound(name);
                        editGoods = seniorCashierView.wantToSmth("enter another name");
                        continue;
                    }
                    order.setGoods(goods);
                    editGoods = seniorCashierView.wantToSmth("remove other good");
                }
                orderService.update(order.getId(), order);
            }
        } catch (DatabaseException e) {
            seniorCashierView.databaseExceptionMessage();
        } catch (InputMismatchException e) {
            seniorCashierView.inputErrorMessage();
        }
    }

    protected void getReport(){
        try {
            String type = seniorCashierView.chooseReportType();
            if(type.equals("x")) {
                int count = reportService.getCount();
                float avg = reportService.getAvg();
                float sum = reportService.getSum();
                seniorCashierView.printXReport(count, avg, sum);
            }
            if (type.equals("z")){
                List<GoodType> countOfTypes = reportService.getAllGoodTypes();
                GoodType mostPopular = reportService.getMostPopular();
                seniorCashierView.printZReport(countOfTypes, mostPopular);
            }

        } catch (DatabaseException e) {
            seniorCashierView.databaseExceptionMessage();;
        }
    }
}


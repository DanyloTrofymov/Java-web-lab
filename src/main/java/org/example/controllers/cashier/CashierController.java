package org.example.controllers.cashier;

import org.example.entities.good.Good;
import org.example.entities.order.Order;
import org.example.entities.order.OrderStatus;
import org.example.exceptions.DatabaseException;
import org.example.services.GoodService;
import org.example.services.OrderService;
import org.example.views.cashier.CashierView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class CashierController {
    protected  CashierView cashierView;
    protected OrderService orderService;
    protected GoodService goodService;


    public CashierController(CashierView cashierView, OrderService orderService, GoodService goodService){
        this.cashierView = cashierView;
        this.orderService = orderService;
        this.goodService = goodService;
    }

    public void start(){
        boolean login = true;
        while (login){
            CashierAction action = cashierView.chooseAction();
            switch (action){
                case CREATE_ORDER -> createOrder();
                case EDIT_ORDER -> editOrder();
                case EDIT_STATUS -> editStatus();
                case FIND_ALL -> findAll();
                case FIND_BY_STATUS -> findByStatus();
                case LOGOUT -> login = false;
            }
        }
    }

    protected void createOrder(){
        try{
            String buyerName = cashierView.getBuyersName();
            List<Good> goods = new ArrayList<>();
            boolean addGoods = true;
            while (addGoods){
                String name = cashierView.addGoods();
                Good good = goodService.findByName(name);
                if(good.isNull()){
                    cashierView.nameNotFound(name);
                    addGoods = cashierView.wantToSmth("add more goods?");
                    continue;
                }
                float quantity = cashierView.getAmount();
                if(quantity <= 0 ) {
                    while (quantity <= 0) {
                        cashierView.illegalQuantity();
                        quantity = cashierView.getAmount();
                    }
                }
                good.setAmount(quantity);
                goods.add(good);
                addGoods = cashierView.wantToSmth("add more");
            }
            Order order = new Order(buyerName, goods);
            orderService.create(order);
            cashierView.smthSeccessfuly("Order created");
        } catch (DatabaseException e) {
            cashierView.databaseExceptionMessage();
        } catch (InputMismatchException e){
            cashierView.inputErrorMessage();
        }
    }

    protected void editOrder(){
        try{
            Order order = findOrder();
            if(order == null){
                return;
            }
            cashierView.print(order.toString());
            boolean editGoods = true;
            while (editGoods){
                String name = cashierView.getGoodName();
                List<Good> goods = order.getGoods();
                Good searched = new Good();
                for(Good good : goods){
                    if(good.getName().equals(name)){
                        searched = good;
                        goods.remove(good);
                        break;
                    }
                }
                if(searched.isNull()){
                    cashierView.nameNotFound(name);
                    editGoods = cashierView.wantToSmth("enter another name?");
                    continue;
                }
                float quantity = cashierView.getAmount();
                if(quantity <= 0 ) {
                    while (quantity <= 0) {
                        cashierView.illegalQuantity();
                        quantity = cashierView.getAmount();
                    }
                }
                searched.setAmount(quantity);
                goods.add(searched);
                order.setGoods(goods);
                editGoods = cashierView.wantToSmth("continue editing");
            }
            orderService.update(order.getId(), order);
            cashierView.smthSeccessfuly("Order edited");
        } catch (DatabaseException e) {
            cashierView.databaseExceptionMessage();
        } catch (InputMismatchException e){
            cashierView.inputErrorMessage();
        }
    }

    public void editStatus() {
        try{
            Order order = findOrder();
            if(order == null){
                return;
            }
            cashierView.print(order.toString());
            OrderStatus status = cashierView.chooseStatus();
            order.setStatus(status);
            orderService.update(order.getId(), order);
            cashierView.smthSeccessfuly("Status edited");
        } catch (DatabaseException e) {
            cashierView.databaseExceptionMessage();
        } catch (InputMismatchException e){
            cashierView.inputErrorMessage();
        }
    }

    protected void findAll(){
        try {
            List<Order> orders = orderService.findAll();
            if(orders.size() == 0){
                cashierView.print("No orders");
            }
            else{
                cashierView.orderList(orders);
            }
        }catch (DatabaseException e) {
            cashierView.databaseExceptionMessage();
        }
    }

    protected void findByStatus() {
        try {
            OrderStatus status = cashierView.chooseStatus();
            List<Order> orders = orderService.findByStatus(status);
            if (orders.size() == 0) {
                cashierView.print("No orders with this status");
            } else {
                cashierView.orderList(orders);
            }
        } catch (DatabaseException e) {
            cashierView.databaseExceptionMessage();
        }
    }

    protected Order findOrder(){
        Order order;
        do{
            String orderId = cashierView.getOrderId();
            order = orderService.findById(orderId);
            if(order.isNull()){
                cashierView.idNotFound(orderId);
                if(!cashierView.wantToSmth("enter another id")) {
                    return null;
                }
            }
        }while(order.isNull());
        return order;
    }
}

package org.example.controllers.cashier;

import org.example.entities.good.Good;
import org.example.entities.order.Order;
import org.example.entities.order.OrderStatus;
import org.example.exceptions.DatabaseException;
import org.example.services.GoodService;
import org.example.services.OrderService;
import org.example.views.cashier.CashierView;

import java.io.IOError;
import java.util.ArrayList;
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
                if(good == null){
                    cashierView.nameNotFound(name);
                    addGoods = cashierView.wantToContinue();
                    continue;
                }
                float quantity = cashierView.getQuantity();
                if(quantity <= 0 ) {
                    while (quantity <= 0) {
                        cashierView.illegalQuantity();
                        quantity = cashierView.getQuantity();
                    }
                }
                good.setAmount(quantity);
                goods.add(good);
                addGoods = cashierView.wantToContinue();
            }
            Order order = new Order(buyerName, goods);
            orderService.create(order);
        } catch (DatabaseException e) {
            System.out.println("Internal server error. ");
        } catch (IOError e){
            System.out.println("Input error");
        }
    }

    protected void editOrder(){
        try{
            Order order = findOrder();
            if(order == null){
                return;
            }
            boolean editGoods = true;
            while (editGoods){
                String name = cashierView.getGoodName();
                List<Good> goods = order.getGoods();
                Good searched = null;
                for(Good good : goods){
                    if(good.getName().equals(name)){
                        searched = good;
                        goods.remove(good);
                    }
                }
                if(searched == null){
                    cashierView.nameNotFound(name);
                    editGoods = cashierView.wantToContinue();
                    continue;
                }
                float quantity = cashierView.getQuantity();
                if(quantity <= 0 ) {
                    while (quantity <= 0) {
                        cashierView.illegalQuantity();
                        quantity = cashierView.getQuantity();
                    }
                }
                searched.setAmount(quantity);
                goods.add(searched);
                order.setGoods(goods);
                editGoods = cashierView.wantToContinue();
            }
            orderService.update(order.getId(), order);
        }
        catch (DatabaseException e) {
            System.out.println("Internal server error. ");
        } catch (IOError e){
            System.out.println("Input error");
        }
    }

    public void editStatus() {
        try{
            Order order = findOrder();
            if(order == null){
                return;
            }
            OrderStatus status = cashierView.chooseStatus();
            order.setStatus(status);
            orderService.update(order.getId(), order);
        }
        catch (DatabaseException e) {
            System.out.println("Internal server error. ");
        } catch (IOError e){
            System.out.println("Input error");
        }
    }

    protected void findAll(){
        try {
            List<Order> orders = orderService.findAll();
            cashierView.orderList(orders);
        }catch (DatabaseException e) {
            System.out.println("Internal server error. ");
        }
    }

    protected void findByStatus(){
        try {
            OrderStatus status = cashierView.chooseStatus();
            List<Order> orders = orderService.findByStatus(status);
            cashierView.orderList(orders);
        }catch (DatabaseException e) {
            System.out.println("Internal server error. ");
        }
    }

    protected Order findOrder(){
        Order order;
        do{
            String orderId = cashierView.getOrderId();
            order = orderService.findById(orderId);
            if(order == null){
                cashierView.idNotFound(orderId);
                if(!cashierView.wantToContinue()) {
                    return null;
                }
            }
        }while(order == null);
        return order;
    }
}

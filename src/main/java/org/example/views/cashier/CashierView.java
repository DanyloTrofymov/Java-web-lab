package org.example.views.user.cashier;

import org.example.controllers.cashier.CashierAction;
import org.example.entities.good.Good;
import org.example.entities.order.Order;
import org.example.entities.order.OrderStatus;
import org.example.views.user.GeneralUserView;

import java.util.List;
import java.util.Scanner;

public class CashierView extends GeneralUserView {
    private final Scanner scanner = new Scanner(System.in);

    public CashierAction chooseAction() {
        CashierAction action = null;
        System.out.println("Choose action:");
        do{
            System.out.println("Enter number \n1: Create order\n2: Edit order\n3: Edit status\n4: Find all orders\n5: Find good by status\n 6: Logout\n");
            String line = scanner.nextLine();
            switch (line){
                case "1" -> action = CashierAction.CREATE_ORDER;
                case "2" -> action = CashierAction.EDIT_ORDER;
                case "3" -> action = CashierAction.EDIT_STATUS;
                case "4" -> action = CashierAction.FIND_ALL;
                case "5" -> action = CashierAction.FIND_BY_STATUS;
                case "6" -> action = CashierAction.LOGOUT;
            }
        } while (action == null);
        return action;
    }

    public OrderStatus chooseStatus() {
        OrderStatus status = null;
        System.out.println("Choose status:");
        do{
            System.out.println("Enter number \n1: REGISTERED,\n2: ACCEPTED\n3: DONE\n");
            String action = scanner.nextLine();
            switch (action) {
                case "1" -> status = OrderStatus.REGISTERED;
                case "2" -> status = OrderStatus.ACCEPTED;
                case "3" -> status = OrderStatus.DONE;
            }
        } while (status == null);
        return status;
    }

    public String getBuyersName(){
        System.out.println("Enter buyer`s name: ");
        return scanner.nextLine();
    }

    public String addGoods(){
        System.out.println("Add goods. Enter good name: ");
        return scanner.nextLine();
    }

    public void orderList(List<Order> orders){
        for(Order order : orders){
            System.out.println(order);
        }
    }
}

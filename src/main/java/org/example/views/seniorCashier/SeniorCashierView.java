package org.example.views.seniorCashier;

import org.example.controllers.seniorCashier.SeniorCashierAction;
import org.example.entities.good.GoodType;
import org.example.entities.order.OrderStatus;
import org.example.views.cashier.CashierView;

import java.util.*;

public class SeniorCashierView extends CashierView {

    private final Scanner scanner = new Scanner(System.in);

    public SeniorCashierAction chooseSeniorAction() {
        SeniorCashierAction action = null;
        System.out.println("\nChoose action:");
        do{
            System.out.println("""
                    Enter number:
                    1: Create order
                    2: Edit order
                    3: Edit order status
                    4: Find all orders
                    5: Find good by status
                    6: Get report
                    7: Logout
                    """);
            String line = scanner.nextLine();
            switch (line){
                case "1" -> action = SeniorCashierAction.CREATE_ORDER;
                case "2" -> action = SeniorCashierAction.EDIT_ORDER;
                case "3" -> action = SeniorCashierAction.EDIT_STATUS;
                case "4" -> action = SeniorCashierAction.FIND_ALL;
                case "5" -> action = SeniorCashierAction.FIND_BY_STATUS;
                case "6" -> action = SeniorCashierAction.GET_REPORT;
                case "7" -> action = SeniorCashierAction.LOGOUT;
            }
        } while (action == null);
        return action;
    }
    @Override
    public OrderStatus chooseStatus() {
        OrderStatus status = null;
        System.out.println("\nChoose status:");
        do{
            System.out.println("""
                               Enter number:
                               1: REGISTERED
                               2: ACCEPTED
                               3: DONE
                               4: RETURNED
                               5: DECLINED
                               """);
            String action = scanner.nextLine();
            switch (action) {
                case "1" -> status = OrderStatus.REGISTERED;
                case "2" -> status = OrderStatus.ACCEPTED;
                case "3" -> status = OrderStatus.DONE;
                case "4" -> status = OrderStatus.RETURNED;
                case "5" -> status = OrderStatus.DECLINED;
            }
        } while (status == null);
        return status;
    }

    public String chooseActionWithGood() {
        String action;
        System.out.println("\nChoose status:");
        do{
            System.out.println("""
            Enter number:
            1: Edit amount of good order
            2: Remove good from order
            """);
            action = scanner.nextLine().toLowerCase();
            if(action.equals("1")){
                return "edit";
            }
            if(action.equals("2")){
                return "remove";
            }
        } while (true);
    }

    public String chooseReportType() {
        String action;
        System.out.println("\nChoose report type:");
        do{
            System.out.println("""
                    Enter letter:
                    X: X-report
                    Z: Z-report
                    """);
            action = scanner.nextLine().toLowerCase();
            if(action.equals("x") || action.equals("z")){
                return action;
            }

        } while (true);
    }

    public void printXReport(int count, float avg, float sum){
        String report = "REPORT X: \nTotal income of a company is: " + sum + "\nCount of orders: " + count + "\nAvg price is: " + avg;
        System.out.println(report);
    }

    public void printZReport(List<GoodType> countOfTypes, GoodType maxOccurredElement){
        System.out.println("REPORT Z: \nFrequency of each category:");
        for (GoodType type : GoodType.values()) {
            System.out.println(type + ": " + Collections.frequency(countOfTypes, type));
        }
        System.out.println("\nThe most popular category is: " + maxOccurredElement);
    }
}

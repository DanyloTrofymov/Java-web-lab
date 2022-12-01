package org.example.views.seniorCashier;

import org.example.controllers.cashier.CashierAction;
import org.example.controllers.seniorCashier.SeniorCashierAction;
import org.example.entities.good.GoodType;
import org.example.entities.order.OrderStatus;
import org.example.views.cashier.CashierView;

import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class SeniorCashierView extends CashierView {

    private final Scanner scanner = new Scanner(System.in);

    public SeniorCashierAction chooseSeniorAction() {
        SeniorCashierAction action = null;
        System.out.println("Choose action:");
        do{
            System.out.println("Enter number \n1: Create order\n2: Edit order\n3: Edit status\n" +
                    "4: Find all orders\n5: Find good by status\n6: Get report \n7: Logout\n");
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
        System.out.println("Choose status:");
        do{
            System.out.println("Enter number \n1: REGISTERED,\n2: ACCEPTED\n3: DONE\n4: RETURNED\n5: DECLINED");
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
        System.out.println("Choose status:");
        do{
            System.out.println("Enter number \n1: Edit quantity\n2: Remove\n");
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
        System.out.println("Choose report type:");
        do{
            System.out.println("Enter letter \nx: X-report\nz: Z-report\n");
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

    public void printZReport(Set<GoodType> countOfTypes, GoodType maxOccurredElement){
        System.out.println("REPORT Z: \nFrequency of each category:");
        for (GoodType type : countOfTypes) {
            System.out.println(countOfTypes + ": " + Collections.frequency(countOfTypes, type));
        }
        System.out.println("\nThe most popular category is: " + maxOccurredElement);
    }
}

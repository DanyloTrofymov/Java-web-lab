package org.example.views.user.seniorCashier;

import org.example.entities.good.GoodType;
import org.example.entities.order.OrderStatus;
import org.example.views.user.cashier.CashierView;

import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class SeniorCashierView extends CashierView {

    private final Scanner scanner = new Scanner(System.in);

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

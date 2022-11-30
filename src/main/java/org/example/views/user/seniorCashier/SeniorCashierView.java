package org.example.views.user.seniorCashier;

import org.example.views.user.GeneralUserView;

import java.util.Scanner;

public class SeniorCashierView extends GeneralUserView {

    private final Scanner scanner = new Scanner(System.in);

    public int chooseAction() {
        int action = -1;
        System.out.println("Choose action:");
        do{
            System.out.println("Enter number \n1: Create order\n2: Edit order\n3: Edit status\n4: Find all orders\n5: Find good by status\n 6: Logout\n");
            action = scanner.nextInt();
        } while (action < 1 || action > 6);
        return action;
    }

    public int chooseStatus() {
        int action = -1;
        System.out.println("Choose status:");
        do{
            System.out.println("Enter number \n1: REGISTERED,\n2: ACCEPTED\n3: DECLINED\n4: RETURNED\n5: DONE\n");
            action = scanner.nextInt();
        } while (action < 1 || action > 5);
        return action;
    }

    public int chooseActionWithGood() {
        int action = -1;
        System.out.println("Choose status:");
        do{
            System.out.println("Enter number \n1: Edit quantity\n2: Remove\n");
            action = scanner.nextInt();
        } while (action < 1 || action > 2);
        return action;
    }

    public int chooseReportType() {
        int action = -1;
        System.out.println("Choose report type:");
        do{
            System.out.println("Enter number \n1: X-report\n2: Z-report\n");
            action = scanner.nextInt();
        } while (action < 1 || action > 2);
        return action;
    }
}

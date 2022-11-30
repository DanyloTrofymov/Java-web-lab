package org.example.views.user.casher;

import org.example.views.user.GeneralUserView;

import java.util.Scanner;

public class CasherUserView extends GeneralUserView {
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
            System.out.println("Enter number \n1: REGISTERED,\n2: ACCEPTED\n3: DONE\n");
            action = scanner.nextInt();
        } while (action < 1 || action > 3);
        return action;
    }

    public String getBuyersName(){
        System.out.println("Enter buyer`s name: ");
        return scanner.nextLine();
    }
}

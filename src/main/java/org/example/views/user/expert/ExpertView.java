package org.example.views.user.expert;

import org.example.entities.good.Good;
import org.example.views.user.GeneralUserView;

import java.util.List;
import java.util.Scanner;

public class ExpertView extends GeneralUserView {
    private final Scanner scanner = new Scanner(System.in);

    public int chooseAction() {
        int action = -1;
        System.out.println("Choose action:");
        do{
            System.out.println("Enter number \n1: Create good\n2: Specify the quantity\n3: Check storage\n4: Find good by type\n5: Find good by name\n 6: Logout\n");
            action = scanner.nextInt();
        } while (action < 0 || action > 6);
        return action;
    }

    public void goodList(List<Good> goods){
        for(Good good : goods){
            System.out.println(good);
        }
    }

    public String getType(){
        System.out.println("Enter type: ");
        return scanner.nextLine();
    }

    public void typeNotFound(String type){
        System.out.println("Type \"" + type + "\" does not exists");
    }

}

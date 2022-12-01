package org.example.views.expert;

import org.example.controllers.expert.ExpertAction;
import org.example.entities.good.Good;
import org.example.entities.good.GoodType;
import org.example.views.MainView;

import java.util.List;
import java.util.Scanner;

public class ExpertView extends MainView {
    private final Scanner scanner = new Scanner(System.in);

    public ExpertAction chooseAction() {
        ExpertAction action = null;
        System.out.println("Choose action:");
        do{
            System.out.println("Enter number \n1: Create good\n2: Specify the quantity\n3: Check storage\n4: Find good by type\n 5: Logout\n");
            String line = scanner.nextLine();
            switch (line){
                case "1" -> action = ExpertAction.CREATE_GOOD;
                case "2" -> action = ExpertAction.EDIT_AMOUNT;
                case "3" -> action = ExpertAction.FIND_ALL;
                case "4" -> action = ExpertAction.FIND_BY_TYPE;
                case "5" -> action = ExpertAction.LOGOUT;
            }
        } while (action == null);
        return action;
    }

    public void goodList(List<Good> goods){
        for(Good good : goods){
            System.out.println(good);
        }
    }

    public GoodType chooseType(){
        System.out.println("Choose type: ");
        GoodType type = null;
        do{
            System.out.println("Enter number \n1: Fruits\n2: Vegitables\n3: Dairy\n4: Meat\n5: Household\n6: Chemicals\n7: Clothing\n8: Other\n");
            String line = scanner.nextLine();
            switch (line){
                case "1" -> type = GoodType.FRUITS;
                case "2" -> type = GoodType.VEGETABLES;
                case "3" -> type = GoodType.DAIRY;
                case "4" -> type = GoodType.MEAT;
                case "5" -> type = GoodType.HOUSEHOLD;
                case "6" -> type = GoodType.CHEMICALS;
                case "7" -> type = GoodType.CLOTHING;
                case "8" -> type = GoodType.OTHER;
            }
        } while (type == null);
        return type;
    }

    public float getPrice(){
        System.out.println("Enter price: ");
        return scanner.nextFloat();
    }

    public float getAmount(){
        System.out.println("Enter amount: ");
        return scanner.nextFloat();
    }

    public void typeNotFound(String type){
        System.out.println("Type \"" + type + "\" does not exists");
    }

    public void alreadyExists(){ System.out.println("Good with this name already exists. Try to add another one."); }
}

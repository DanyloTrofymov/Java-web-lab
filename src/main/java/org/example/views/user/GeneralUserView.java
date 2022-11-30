package org.example.views.user;

import java.util.Scanner;

public class GeneralUserView {

    private final Scanner scanner = new Scanner(System.in);

    public String getGoodName(){
        System.out.println("Enter good name: ");
        return scanner.nextLine();
    }

    public void nameNotFound(String name){
        System.out.println("Good with name \"" + name + "\" does not exists");
    }

    public String getOrderId(){
        System.out.println("Enter order ID: ");
        return scanner.nextLine();
    }

    public void idNotFound(String id){
        System.out.println("Order with id \"" + id + "\" does not exists");
    }

    public float getQuantity(){
        System.out.println("Enter quantity: ");
        return scanner.nextFloat();
    }

    public void illegalQuantity(){
        System.out.println("Quantity must be a float number that greater then  0: ");
    }

    public boolean wantToContinue(){
        do{
            System.out.println("Do you want to continue? (Y/N): ");
            String line = scanner.nextLine().toLowerCase();
            switch (line){
                case "y" -> {
                    return true;
                }
                case "n" -> {
                    return false;
                }
            }
        }
        while(true);
    }
}

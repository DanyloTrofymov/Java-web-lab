package org.example.views.user;

import java.util.Scanner;

public class GeneralUserView {

    private final Scanner scanner = new Scanner(System.in);

    public String getName(){
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
}

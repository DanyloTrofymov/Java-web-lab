package org.example.views;

import java.util.Scanner;

public class MainView {

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

    public float getAmount(){
        System.out.println("Enter amount: ");
        float quantity = scanner.nextFloat();
        scanner.nextLine();
        return quantity;
    }

    public void illegalQuantity(){
        System.out.println("Quantity must be a float number that greater then 0!");
    }

    public void illegalPrice(){
        System.out.println("Price must be a float number that greater then 0!");
    }

    public boolean wantToSmth(String smth){
        do{
            System.out.println("Do you want to " + smth + "? (Y/N): ");
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

    public void smthSeccessfuly(String smth){
        System.out.println(smth + " successfully!");
    }

    public void print(String str){ System.out.println(str); }
    public void databaseExceptionMessage(){
        System.out.println("\nINTERNAL SERVER ERROR\n");
    }

    public void inputErrorMessage(){
        System.out.println("\nINPUT ERROR\n");
    }
}

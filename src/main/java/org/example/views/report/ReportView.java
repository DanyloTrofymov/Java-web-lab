package org.example.views.report;

import org.example.entities.good.GoodType;

import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class ReportView {

    private final Scanner scanner = new Scanner(System.in);

    public String chooseReport() {
        System.out.println("Please choose the report. Enter X or Y and press Enter. ");
        return scanner.nextLine();
    }

    public void printXReport(int count, float avg, float sum){
        String report = "REPORT X: \nTotal income of a company is: " + sum + "\nCount of orders: " + count + "\nAvg price is: " + avg;
        System.out.println(report);
    }

    public void printYReport(Set<GoodType> countOfTypes, GoodType maxOccurredElement){
        System.out.println("REPORT X: \nFrequency of each category:");
        for (GoodType type : countOfTypes) {
            System.out.println(countOfTypes + ": " + Collections.frequency(countOfTypes, type));
        }
        System.out.println("\nThe most popular category is: " + maxOccurredElement);
    }
}

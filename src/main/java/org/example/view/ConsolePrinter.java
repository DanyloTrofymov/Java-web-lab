package org.example.view;

public class ConsolePrinter {
    public static final String enterPathMessage = "Please enter the path \n";
    public static final String pathDoesNotExists = "This path does not exists. Try again. \n";
    public static final String success = "Files are formatted successfuly. \n";
    public static final String wantToContinue = "If you want to continue type \"Y\" \n.";
    public void print(String value) {
        System.out.print(value);
    }
}

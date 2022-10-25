package org.example.view;

import java.util.Scanner;

public class View {
    private final Scanner scanner;
    private final ConsolePrinter printer;
    public View(ConsolePrinter printer) {
        this.printer = printer;
        scanner = new Scanner(System.in);
    }

    public String getPath() {
        printer.print(ConsolePrinter.enterPathMessage);
        return scanner.nextLine();
    }

    public boolean wantToContinue(){
        printer.print(ConsolePrinter.wantToContinue);
        String answ = scanner.next();
        return answ.equalsIgnoreCase("y");
    }
}

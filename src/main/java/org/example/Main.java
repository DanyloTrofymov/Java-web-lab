package org.example;

import org.example.controller.Controller;
import org.example.view.ConsolePrinter;
import org.example.view.View;


// D:\Инивурситет\Java web 2022\EXAMPLES
public class Main {
    public static void main(String[] args) {
        ConsolePrinter printer = new ConsolePrinter();
        View view = new View(printer);
        Controller controller  = new Controller(view, printer);

        controller.start();
    }
}
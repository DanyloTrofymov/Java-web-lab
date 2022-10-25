package org.example.controller;

import org.example.service.SpaceRemover;
import org.example.service.Validator;
import org.example.view.ConsolePrinter;
import org.example.view.View;

public class Controller {
    private final View view;
    private final ConsolePrinter printer;
    private final Validator validator = new Validator();
    public Controller(View view, ConsolePrinter printer){
            this.view = view;
            this.printer = printer;
    }

    public void start() {
        boolean work = true;
        do {
            String dirPath = view.getPath();
            if (!validator.dirPathIsValid(dirPath)) {
                printer.print(ConsolePrinter.pathDoesNotExists);
            }
            SpaceRemover spaceRemover = new SpaceRemover(dirPath, printer);
            try {
                spaceRemover.remove();
                printer.print(ConsolePrinter.success);
            }
            catch (Exception e) {
                printer.print(e.getMessage());
            }
            if(!view.wantToContinue()){
                work = false;
            }

        } while (work);
    }
}

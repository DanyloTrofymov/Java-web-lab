package org.example.service;

import org.example.view.ConsolePrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Future;

public class SpaceRemover {
    private final String path;
    private final ConsolePrinter printer;
    public SpaceRemover(String path, ConsolePrinter printer) {
        this.path = path;
        this.printer = printer;
    }

    public void remove() throws Exception {
        File file = new File(this.path);
        ExecutorService executorService = Executors.newCachedThreadPool();
        findAllFilesInDirectory(file, executorService);
        executorService.shutdown();
    }

    private int findAllFilesInDirectory(File file, ExecutorService executorService) throws Exception {
        if (file.isDirectory()) {
            File[] allFiles = file.listFiles();
            List<Future<Integer>> allTasks = new ArrayList<>();
            for (var currentFile : allFiles) {
                Callable<Integer> recursiveTask = () -> findAllFilesInDirectory(currentFile, executorService);
                Future<Integer> submit = executorService.submit(recursiveTask);
                allTasks.add(submit);
            }
            int result = 0;
            for (var task : allTasks) {
                result += task.get();
            }
            return result;
        }
        if (file.getName().endsWith(".java")) {
            removeSpaces(file, path);
        }
        return 0;

    }

    private void removeSpaces(File file, String path) throws FileNotFoundException {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy-HHmmss");
        String formattedDate = date.format(format);
        String resultPath = path + File.separator + "result-" + formattedDate + File.separator + file.getName();
        PrintWriter printWriter = new PrintWriter(resultPath);

        printer.print(file.getAbsolutePath() + "\n");

        Scanner scanner = new Scanner(file);
        int firstSpaces = 0;
        while (scanner.hasNextLine()) {
            char prevSymb = Character.MIN_VALUE;
            String str = scanner.nextLine();
            char[] charArr = str.stripTrailing().toCharArray();
            int tempFisrtSpacesCounter = firstSpaces;
            for (int i = 0; i < charArr.length; i++) {
                char symbol = charArr[i];
                if (i < tempFisrtSpacesCounter && symbol == ' ') {
                    continue;
                } else if (i < tempFisrtSpacesCounter) {
                    tempFisrtSpacesCounter = 0;
                }
                if (symbol == '{') {
                    firstSpaces += 4;
                }
                if (symbol == '}') {
                    firstSpaces -= 4;
                }
                if (prevSymb == ' ' && symbol == ' ') {
                    charArr[i] = Character.MIN_VALUE;
                }
                prevSymb = charArr[i];
            }
            printWriter.println(charArr.toString());
        }
        printWriter.close();
        scanner.close();
    }
}

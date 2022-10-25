package org.example.service;

import java.io.File;

public class Validator {
    public boolean dirPathIsValid(String dirPath) {
        File file = new File(dirPath);
        return file.exists() && file.isDirectory();
    }
}

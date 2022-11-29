package org.example.exceptions;

public class DatabaseException extends RuntimeException{
    public DatabaseException(Throwable cause) {
        super(cause);
    }
}

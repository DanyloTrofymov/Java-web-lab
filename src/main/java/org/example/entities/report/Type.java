package org.example.entities.report;

public enum Type {
    X,
    Z;

    public static org.example.entities.report.Type getType(String value) {
        if(value == null){
            return null;
        }
        return switch (value) {
            case "X" -> X;
            case "Z" -> Z;
            default -> null;
        };
    }
}

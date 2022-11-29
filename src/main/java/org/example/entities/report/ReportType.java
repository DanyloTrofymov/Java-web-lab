package org.example.entities.report;

public enum ReportType {
    X,
    Z;

    public static ReportType getType(String value) {
        if(value == null){
            return null;
        }
        return switch (value.toLowerCase()) {
            case "x" -> X;
            case "z" -> Z;
            default -> null;
        };
    }
}

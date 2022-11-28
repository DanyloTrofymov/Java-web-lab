package org.example.entities.good;

public enum Type {
    FRUITS,
    VEGETABLES,
    DAIRY,
    MEAT,
    HOUSEHOLD,
    CHEMICALS,
    CLOTHING,
    OTHER;

    public static Type getType(String value) {
        if(value == null){
            return null;
        }
        return switch (value) {
            case "FRUITS" -> FRUITS;
            case "VEGETABLES" -> VEGETABLES;
            case "DAIRY" -> DAIRY;
            case "MEAT" -> MEAT;
            case "HOUSEHOLD" -> HOUSEHOLD;
            case "CHEMICALS" -> CHEMICALS;
            case "CLOTHING" -> CLOTHING;
            case "OTHER" -> OTHER;
            default -> null;
        };
    }
}

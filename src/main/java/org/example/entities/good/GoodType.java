package org.example.entities.good;

public enum GoodType {
    FRUITS,
    VEGETABLES,
    DAIRY,
    MEAT,
    HOUSEHOLD,
    CHEMICALS,
    CLOTHING,
    OTHER;

    public static GoodType getType(String value) {
        if(value == null){
            return null;
        }
        return switch (value.toLowerCase()) {
            case "fruits" -> FRUITS;
            case "vegetables" -> VEGETABLES;
            case "dairy" -> DAIRY;
            case "meat" -> MEAT;
            case "household" -> HOUSEHOLD;
            case "chemicals" -> CHEMICALS;
            case "clothing" -> CLOTHING;
            case "other" -> OTHER;
            default -> null;
        };
    }
}

package org.example.entities.order;

import org.example.entities.good.GoodType;

public enum OrderStatus {
    REGISTERED,
    ACCEPTED,
    DECLINED,
    RETURNED,
    DONE;
    public static OrderStatus getStatus(String value) {
        if(value == null){
            return null;
        }
        return switch (value.toLowerCase()) {
            case "registered" -> REGISTERED;
            case "accepted" -> ACCEPTED;
            case "declined" -> DECLINED;
            case "returned" -> RETURNED;
            case "done" -> DONE;
            default -> null;
        };
    }
}

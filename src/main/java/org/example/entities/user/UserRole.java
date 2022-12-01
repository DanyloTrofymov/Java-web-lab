package org.example.entities.user;

public enum UserRole {

    CASHIER,
    SENOR_CASHIER,
    EXPERT;

   public static UserRole getRole(String value) {
       if(value == null){
           return null;
       }
       return switch (value.toLowerCase()) {
           case "cashier" -> CASHIER;
           case "senior_cashier" -> SENOR_CASHIER;
           case "expert" -> EXPERT;
           default -> null;
       };
       
   }
}

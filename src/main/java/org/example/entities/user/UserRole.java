package org.example.entities.user;

public enum UserRole {

    CASHER,
    SENOR_CASHER,
    EXPERT;

   public static UserRole getRole(String value) {
       if(value == null){
           return null;
       }
       return switch (value.toLowerCase()) {
           case "casher" -> CASHER;
           case "senior_casher" -> SENOR_CASHER;
           case "expert" -> EXPERT;
           default -> null;
       };
       
   }
}

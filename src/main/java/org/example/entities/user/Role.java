package org.example.entities.user;

public enum Role {

    CASHER,
    SENOR_CASHER,
    EXPERT;

   public static Role getRole(String value) {
       if(value == null){
           return null;
       }
       return switch (value) {
           case "CASHER" -> CASHER;
           case "SENOR_CASHER" -> SENOR_CASHER;
           case "EXPERT" -> EXPERT;
           default -> null;
       };
   }
}

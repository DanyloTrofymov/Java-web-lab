package org.example.entities.user;

public enum Role {

    CASHER,
    SENOR_CASHER,
    EXPERT;

   public static Role getRole(String value) {
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

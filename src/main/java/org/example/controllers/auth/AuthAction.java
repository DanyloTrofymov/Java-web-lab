package org.example.controllers.auth;

public enum AuthAction {

    LOGIN,
    REGISTER,
    BACK_TO_CHOOSE;

    public static AuthAction getAction(String value){
        if(value == null){
            return null;
        }
        return switch (value.toLowerCase()){
            case "login" -> LOGIN;
            case "register" -> REGISTER;
            case "back" -> BACK_TO_CHOOSE;
            default -> null;
        };
    }
}

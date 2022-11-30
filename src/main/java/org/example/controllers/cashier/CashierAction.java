package org.example.controllers.cashier;

import org.example.controllers.auth.AuthAction;

public enum CashierAction {
    CREATE_ORDER,
    EDIT_ORDER,
    EDIT_STATUS,
    FIND_ALL,
    FIND_BY_STATUS,
    LOGOUT;

    public static CashierAction getAction(String value){
        if(value == null){
            return null;
        }
        return switch (value.toLowerCase()){
            case "create_order" -> CREATE_ORDER;
            case "edit_order" -> EDIT_ORDER;
            case "change_status" -> EDIT_STATUS;
            case "find_all" -> FIND_ALL;
            case "find_by_status" -> FIND_BY_STATUS;
            case "logout" -> LOGOUT;
            default -> null;
        };
    }

}

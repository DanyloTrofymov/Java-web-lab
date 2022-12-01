package org.example.controllers;

import org.example.controllers.auth.AuthController;
import org.example.controllers.cashier.CashierController;
import org.example.controllers.expert.ExpertController;
import org.example.controllers.seniorCashier.SeniorCashierController;
import org.example.entities.user.User;
import org.example.repositories.dao.AbstractConnectionManager;
import org.example.repositories.dao.AbstractDaoGoodService;
import org.example.repositories.dao.AbstractDaoOrderService;
import org.example.repositories.dao.AbstractDaoUserService;
import org.example.services.GoodService;
import org.example.services.OrderService;
import org.example.services.ReportService;
import org.example.services.UserService;
import org.example.services.auth.LoginService;
import org.example.services.auth.RegisterService;
import org.example.views.MainView;
import org.example.views.auth.AuthView;
import org.example.views.cashier.CashierView;
import org.example.views.expert.ExpertView;
import org.example.views.seniorCashier.SeniorCashierView;

public class MainController {
    AbstractConnectionManager daoConnectionManager;
    AbstractDaoGoodService daoGoodService;
    AbstractDaoUserService daoUserService;
    AbstractDaoOrderService daoOrderService;

    public MainController(AbstractConnectionManager daoConnectionManager, AbstractDaoGoodService daoGoodService, AbstractDaoUserService daoUserService, AbstractDaoOrderService daoOrderService) {
        this.daoConnectionManager = daoConnectionManager;
        this.daoGoodService = daoGoodService;
        this.daoUserService = daoUserService;
        this.daoOrderService = daoOrderService;
    }

    public void start(){
        MainView view = new MainView();
        do {
            User user = authorize();
            switch (user.getRole()) {
                case CASHIER -> cashierMenu();
                case SENOR_CASHIER -> seniorCashierMenu();
                case EXPERT -> expertMenu();
            }
        } while (view.wantToContinue());
    }

    private void cashierMenu(){
        CashierView cashierView = new CashierView();
        OrderService orderService = new OrderService(daoOrderService);
        GoodService goodService = new GoodService(daoGoodService);
        CashierController cashierController = new CashierController(cashierView, orderService, goodService);

        cashierController.start();
    }

    private void seniorCashierMenu(){
        SeniorCashierView seniorCashierView = new SeniorCashierView();
        OrderService orderService = new OrderService(daoOrderService);
        GoodService goodService = new GoodService(daoGoodService);
        ReportService reportService = new ReportService(daoOrderService, daoGoodService);
        SeniorCashierController seniorCashierController = new SeniorCashierController(seniorCashierView, orderService, goodService, reportService);

        seniorCashierController.start();
    }

    private void expertMenu(){
        ExpertView expertView = new ExpertView();
        GoodService goodService = new GoodService(daoGoodService);
        ExpertController expertController = new ExpertController(expertView, goodService);

        expertController.start();
    }

    private User authorize(){
        AuthView authView = new AuthView();
        LoginService loginService = new LoginService(daoUserService);
        RegisterService registerService = new RegisterService(daoUserService);
        UserService userService = new UserService(daoUserService);
        AuthController authController = new AuthController(authView, loginService, registerService, userService);

        return authController.start();
    }
}

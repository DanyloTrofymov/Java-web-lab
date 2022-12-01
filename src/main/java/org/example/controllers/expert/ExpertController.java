package org.example.controllers.expert;

import org.example.entities.good.Good;
import org.example.entities.good.GoodType;
import org.example.exceptions.DatabaseException;
import org.example.services.GoodService;
import org.example.views.expert.ExpertView;

import java.io.IOError;
import java.util.InputMismatchException;
import java.util.List;

public class ExpertController {
    ExpertView expertView;
    GoodService goodService;


    public ExpertController(ExpertView expertView, GoodService goodService){
        this.expertView = expertView;
        this.goodService = goodService;
    }

    public void start(){
        boolean login = true;
        while (login){
            ExpertAction action = expertView.chooseAction();
            switch (action){
                case CREATE_GOOD -> createGood();
                case EDIT_AMOUNT -> editAmount();
                case FIND_ALL -> findAll();
                case FIND_BY_TYPE -> findByType();
                case LOGOUT -> login = false;
            }
        }
    }

    protected void createGood(){
        try{
            boolean addGoods = true;
            while (addGoods){
                String goodName = expertView.getGoodName();
                Good search = goodService.findByName(goodName);
                if(!search.isNull()){
                    expertView.alreadyExists();
                    addGoods = expertView.wantToSmth("add another good");
                    continue;
                }
                GoodType type = expertView.chooseType();
                float amount = expertView.getAmount();
                if(amount <= 0 ) {
                    while (amount <= 0) {
                        expertView.illegalQuantity();
                        amount = expertView.getAmount();
                    }
                }
                float price = expertView.getPrice();
                if(price <= 0 ) {
                    while (price <= 0) {
                        expertView.illegalPrice();
                        price = expertView.getPrice();
                    }
                }
                Good good = new Good(goodName, type, price, amount);
                goodService.create(good);
                expertView.smthSeccessfuly("Good added");
                addGoods = expertView.wantToSmth("add more");
            }
        } catch (DatabaseException e) {
            expertView.databaseExceptionMessage();
        } catch (InputMismatchException e){
            expertView.inputErrorMessage();
        }
    }

    protected void editAmount(){
        try{
            boolean editAmount = true;
            while (editAmount) {
                Good good = findGood();
                if(good == null){
                    return;
                }
                float amount = expertView.getAmount();
                good.setAmount(amount);
                goodService.update(good.getId(), good);
                expertView.smthSeccessfuly("Amount updated");
                editAmount = expertView.wantToSmth("edit other goods");
            }
        } catch (DatabaseException e) {
            expertView.databaseExceptionMessage();
        } catch (InputMismatchException e){
            expertView.inputErrorMessage();
        }
    }

    protected void findAll(){
        try {
            List<Good> goods = goodService.findAll();
            if(goods.size() == 0){
                expertView.print("No goods");
            }
            else {
                expertView.goodList(goods);
            }
        } catch (DatabaseException e) {
            expertView.databaseExceptionMessage();
        }
    }

    protected void findByType(){
        try {
            GoodType type = expertView.chooseType();
            List<Good> goods = goodService.findByType(type);
            if(goods.size() == 0){
                expertView.print("No goods with this type");
            }
            else {
                expertView.goodList(goods);
            }
        } catch (DatabaseException e) {
            expertView.databaseExceptionMessage();
        }
    }

    protected Good findGood(){
        Good good;
        do{
            String name = expertView.getGoodName();
            good = goodService.findByName(name);
            if(good.isNull()){
                expertView.nameNotFound(name);
                if(!expertView.wantToSmth("enter another name")) {
                    return null;
                }
            }
        }while(good.isNull());
        return good;
    }
}

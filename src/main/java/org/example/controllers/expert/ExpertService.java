package org.example.controllers.expert;

import org.example.entities.good.Good;
import org.example.entities.good.GoodType;
import org.example.exceptions.DatabaseException;
import org.example.services.GoodService;
import org.example.views.user.expert.ExpertView;

import java.io.IOError;
import java.util.List;

public class ExpertService {
    ExpertView expertView;
    GoodService goodService;


    public ExpertService(ExpertView expertView, GoodService goodService){
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

    private void createGood(){
        try{
            boolean addGoods = true;
            while (addGoods){
                String goodName = expertView.getGoodName();
                Good search = goodService.findByName(goodName);
                if(search != null){
                    expertView.alreadyExists();
                    continue;
                }
                GoodType type = expertView.chooseType();
                float quantity = expertView.getQuantity();
                if(quantity <= 0 ) {
                    while (quantity <= 0) {
                        expertView.illegalQuantity();
                        quantity = expertView.getQuantity();
                    }
                }
                float price = expertView.getQuantity();
                if(price <= 0 ) {
                    while (price <= 0) {
                        expertView.illegalQuantity();
                        price = expertView.getQuantity();
                    }
                }
                Good good = new Good(goodName, price, quantity);
                goodService.create(good);
                addGoods = expertView.wantToContinue();
            }
        } catch (DatabaseException e) {
            System.out.println("Internal server error.");
        } catch (IOError e){
            System.out.println("Input error");
        }
    }

    private void editAmount(){
        try{
            Good good = findGood();
            float price = expertView.getPrice();
            good.setPrice(price);
            goodService.update(good.getId(), good);
        }
        catch (DatabaseException e) {
            System.out.println("Internal server error. ");
        } catch (IOError e){
            System.out.println("Input error");
        }
    }

    private void findAll(){
        try {
            List<Good> goods = goodService.findAll();
            expertView.goodList(goods);
        }catch (DatabaseException e) {
            System.out.println("Internal server error. ");
        }
    }

    private void findByType(){
        try {
            GoodType type = expertView.chooseType();
            List<Good> goods = goodService.findByType(type);
            expertView.goodList(goods);
        }catch (DatabaseException e) {
            System.out.println("Internal server error. ");
        }
    }

    private Good findGood(){
        Good good;
        do{
            String name = expertView.getGoodName();
            good = goodService.findByName(name);
            if(good == null){
                expertView.nameNotFound(name);
            }
        }while(good == null);
        return good;
    }
}

package org.example.services;

import org.example.entities.good.Good;
import org.example.entities.good.GoodType;
import org.example.repositories.dao.AbstractDaoGoodService;

import java.util.List;

public class GoodService {
    private final AbstractDaoGoodService goodService;

    public GoodService(AbstractDaoGoodService service) { this.goodService = service; }

    public void create(Good good) { goodService.create(good); }

    public List<Good> findAll() { return goodService.findAll(); }

    public Good findById(String id) { return goodService.findById(id); }

    public void update(String id, Good good) { goodService.update(id, good); }

    public void delete(String id) { goodService.delete(id); }

    public List<Good> findByType(GoodType good) { return goodService.findByType(good); }

    public Good findByName(String name) { return goodService.findByName(name); }
}



package org.example.services;

import org.example.entities.good.Good;
import org.example.entities.good.GoodType;
import org.example.repositories.dao.AbstractGoodService;

import java.util.List;

public class GoodService {
    private final AbstractGoodService goodService;

    public GoodService(AbstractGoodService service) { this.goodService = service; }

    public void create(Good good) { goodService.create(good); }

    public List<Good> findAll() { return goodService.findAll(); }

    public Good findById(int id) { return goodService.findById(id); }

    public void update(int id, Good good) { goodService.update(id, good); }

    public void delete(int id) { goodService.delete(id); }

    public List<Good> findByType(GoodType good) { return goodService.findByType(good); }

}



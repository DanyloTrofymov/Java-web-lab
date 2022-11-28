package org.example.repositories.dao;

import java.util.List;

public abstract class AbstractDAO <T>{
    public abstract void create(T t);
    public abstract List<T> getAll();
    public abstract T getById(int id);
    public abstract void update(int id, T t);
    public abstract void delete(T t);
}

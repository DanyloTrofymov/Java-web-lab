package org.example.repositories.dao;

import org.example.entities.user.User;

import java.util.List;

public interface CrudDao<T> {

    void create(T value);

    void delete(String id);

    void update(String id, T newValue);

    List<T> findAll();

    T findById(String id);

}

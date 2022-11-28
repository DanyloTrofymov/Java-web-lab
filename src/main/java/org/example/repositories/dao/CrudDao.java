package org.example.repositories.dao.cruddao;

import org.example.entities.user.User;

import java.util.List;

public interface CrudDao<T> {

    T insert(T value);

    void delete(int id);

    void update(int id, T newValue);

    List<T> findAll();

    T findById(int id);

}

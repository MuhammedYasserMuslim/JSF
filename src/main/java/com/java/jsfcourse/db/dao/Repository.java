package com.java.jsfcourse.db.dao;

import java.util.List;

public interface Repository<T, ID extends Number> {

    List<T> findAll();

    T findById(ID id);

    void save(T t);

    void deleteById(ID id);
}

package com.netcracker.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, ID extends Serializable> {

    void save(T t);

    void delete(ID id);

    void update(T t);

    List<T> getAllEntities();

    T getEntityById(ID id);
}

package com.netcracker.service;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T, ID extends Serializable> {

    /**
     *
     * @param t
     */
    void save(T t);

    /**
     *
     * @param id
     */
    void delete(ID id);

    /**
     *
     * @param t
     */
    void update(T t);

    /**
     *
     * @return
     */
    List<T> getAllEntities();

    /**
     *
     * @param id
     * @return
     */
    T getEntityById(ID id);
}

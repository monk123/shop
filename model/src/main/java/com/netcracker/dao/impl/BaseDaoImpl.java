package com.netcracker.dao.impl;

import com.netcracker.dao.BaseDao;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import java.util.List;

/**
 * Implementation of {@link BaseDao} interface
 *
 * @author Ayupov Vadim
 * @version 1.0
 *
 * @param <T>
 * @param <ID>
 */

@Log
@Repository
public abstract class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private final Class<? extends T> baseDaoType;

    public BaseDaoImpl() {
        this.baseDaoType = (Class<T>) ((ParameterizedType)
                getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * this method returns object of the current session
     */
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * save entity in the database
     * @param t
     */
    @Override
    public void save(T t) {
        getCurrentSession().persist(t);
        log.info(baseDaoType + " successfully save: " + t);
    }

    /**
     * delete entity from database
     * @param id
     */
    @Override
    public void delete(ID id) {
        T t = getCurrentSession().get(baseDaoType, id);

        log.info(baseDaoType + " successfully get by id: " + id);

        if (t != null) {

            getCurrentSession().delete(t);

            log.info(baseDaoType + " successfully delete: " + t);
        }
    }

    /**
     * update entity into the database
     * @param t
     */
    @Override
    public void update(T t) {
        log.info(baseDaoType + " successfully update: " + t);
        getCurrentSession().merge(t);
    }

    /**
     * displays all entity in the database
     * @return
     */
    @Override
    public List<T> getAllEntities() {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery((Class<T>) baseDaoType);
        Root<T> root = criteria.from((Class<T>) baseDaoType);
        criteria.select(root);
        List<T> list = session.createQuery(criteria).list();
        list.forEach(t -> log.info(baseDaoType + " list: " + t));
        return list;
    }

    /**
     * findCategoryByCategoryName entity by id from database
     * @param id
     * @return
     */
    @Override
    public T getEntityById(ID id) {
        T t = getCurrentSession().get(baseDaoType, id);
        log.info(baseDaoType + " successfully get by id: " + id);
        return t;
    }
}

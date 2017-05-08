package com.netcracker.dao.impl;

import com.netcracker.dao.BaseDao;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(propagation = Propagation.REQUIRED) @Log
public class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {
    private SessionFactory sessionFactory;

    protected Class<? extends T> baseDaoType;

    public BaseDaoImpl() {
        this.baseDaoType = (Class<T>) ((ParameterizedType)
                getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /**
     * this method returns object of the current session
     */
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * save entity in the database
     * @param t
     */
    @Override
    public void save(T t) {
        currentSession().save(t);
        log.info(baseDaoType + " successfully save: " + t);
    }

    /**
     * delete entity from database
     * @param id
     */
    @Override
    public void delete(ID id) {
        T t = currentSession().get(baseDaoType, id);

        log.info(baseDaoType + " successfully get by id: " + id);

        if (t != null) {

            currentSession().delete(t);

            log.info(baseDaoType + " successfylly delete: " + t);
        }
    }

    /**
     * update entity into the database
     * @param t
     */
    @Override
    public void update(T t) {
        log.info(baseDaoType + " successfully update: " + t);
        currentSession().update(t);
    }

    /**
     * displays all entity in the database
     * @return
     */
    @Override
    public List<T> getAllEntities() {
        Session session = currentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery((Class<T>) baseDaoType);
        Root<T> root = criteria.from((Class<T>) baseDaoType);
        criteria.select(root);
        List<T> list = session.createQuery(criteria).list();

        if (!list.isEmpty() && list != null) {
            list.forEach(t -> log.info(baseDaoType + " list: " + t));
            return list;
        }

        return null;
    }

    /**
     * findCategoryByCategoryName entity by id from database
     * @param id
     * @return
     */
    @Override
    public T getEntityById(ID id) {
        T t = currentSession().get(baseDaoType, id);
        log.info(baseDaoType + " successfully get by id: " + id);
        return t;
    }
}

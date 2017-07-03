package com.netcracker.service.impl;

import com.netcracker.dao.BaseDao;
import com.netcracker.service.BaseService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Log
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T,ID> {

    private BaseDao<T, ID> baseDao;

    @Autowired
    public void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    public BaseServiceImpl() {
    }

    @Override
    @Transactional
    public void save(T t) {
        baseDao.save(t);
        log.info(t.getClass() + " successfully saved: " + t);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        baseDao.delete(id);
        log.info(getClass() + "successfully delete by id: " + id);
    }

    @Override
    @Transactional
    public void update(T t) {
        baseDao.update(t);
        log.info(getClass() + "successfully update: " + t);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAllEntities() {
        return baseDao.getAllEntities();
    }

    @Override
    @Transactional(readOnly = true)
    public T getEntityById(ID id) {
        return baseDao.getEntityById(id);
    }
}

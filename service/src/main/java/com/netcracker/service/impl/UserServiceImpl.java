package com.netcracker.service.impl;

import com.netcracker.dao.UserDao;
import com.netcracker.pojo.User;
import com.netcracker.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<User> paginationUser(int firstValue, int maxValue) {
        return userDao.paginationUser(firstValue, maxValue);
    }

    @Override
    @Transactional
    public User loadByUsername(String username) {
        return userDao.loadByUsername(username);
    }
}

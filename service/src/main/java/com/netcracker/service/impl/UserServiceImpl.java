package com.netcracker.service.impl;

import com.netcracker.dao.AddressDao;
import com.netcracker.dao.RoleDao;
import com.netcracker.dao.UserDao;
import com.netcracker.pojo.Role;
import com.netcracker.pojo.User;
import com.netcracker.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Implementation of {@link UserService} and extends {@link BaseServiceImpl}
 *
 * @author Ayupov Vadim
 */

@Log
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    private UserDao userDao;
    private AddressDao addressDao;
    private RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, AddressDao addressDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.addressDao = addressDao;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> paginationUser(int firstValue, int maxValue) {
        return userDao.paginationUser(firstValue, maxValue);
    }

    @Override
    @Transactional(readOnly = true)
    public User loadByUsername(String username) {
        return userDao.loadByUsername(username);
    }

    @Override
    @Transactional
    public void editUserInformation(User user) {
        if(user.getAddress().getId() == null) {
            addressDao.save(user.getAddress());
        } else {
            addressDao.update(user.getAddress());
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getEntityById(2L));
        user.setRoles(roles);
        userDao.update(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User loadUserByEmail(String email) {
        return userDao.loadUserByEmail(email);
    }

}

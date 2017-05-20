package com.netcracker.service.impl;

import com.netcracker.dao.AddressDao;
import com.netcracker.dao.RoleDao;
import com.netcracker.dao.UserDao;
import com.netcracker.pojo.Address;
import com.netcracker.pojo.Role;
import com.netcracker.pojo.User;
import com.netcracker.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    private UserDao userDao;
    private AddressDao addressDao;
    private RoleDao roleDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    @Autowired
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
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

    @Override
    @Transactional
    public void insertInformationAboutUser(User user) {

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
    @Transactional
    public User loadUserByEmail(String email) {
        return userDao.loadUserByEmail(email);
    }
}

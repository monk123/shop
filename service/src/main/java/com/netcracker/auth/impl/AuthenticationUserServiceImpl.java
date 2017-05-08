package com.netcracker.auth.impl;

import com.netcracker.auth.AuthenticationUserService;
import com.netcracker.dao.RoleDao;
import com.netcracker.dao.UserDao;
import com.netcracker.pojo.Role;
import com.netcracker.pojo.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link AuthenticationUserService} interface
 *
 * @author Ayupov Vadim
 */

@Service @Log
public class AuthenticationUserServiceImpl implements AuthenticationUserService {

    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * this method is needed to save user
     * @param user
     */
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getEntityById(2L));
        user.setRoles(roles);
        userDao.save(user);
        log.info("User successfully save: " + user);
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return userDao.loadByUsername(username);
    }
}

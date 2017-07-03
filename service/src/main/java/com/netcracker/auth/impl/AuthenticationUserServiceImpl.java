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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link AuthenticationUserService} interface
 *
 * @author Ayupov Vadim
 */

@Log
@Service
public class AuthenticationUserServiceImpl implements AuthenticationUserService {

    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthenticationUserServiceImpl(UserDao userDao, RoleDao roleDao,
                                         BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getEntityById(2L));
        user.setRoles(roles);
        userDao.save(user);
        log.info("User successfully save: " + user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userDao.loadByUsername(username);
    }
}

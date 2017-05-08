package com.netcracker.auth.impl;

import com.netcracker.dao.UserDao;
import com.netcracker.pojo.Role;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link UserDetailsService} interface
 *
 * @author Ayupov Vadim
 */

@Log
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * The UserDetailsService interface is used to retrieve user-related data.
     * It has one method named loadUserByUsername() which finds a user entity based on the username and
     * can be overridden to customize the process of finding the user
     * @param name
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        com.netcracker.pojo.User user = userDao.loadByUsername(name);
        log.info("User info: " + user);

        List<GrantedAuthority> authorities = getGrantedAuthority(user.getRoles());

        return buildUserForAuthentication(user, authorities);
    }

    /**
     * this method is needed to convert {@link com.netcracker.pojo.User}
     * to {@link org.springframework.security.core.userdetails.User}
     *
     * @param user
     * @param authorities
     * @return
     */
    private User buildUserForAuthentication(com.netcracker.pojo.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), true,
                true, true, true, authorities);
    }

    /**
     * build user's authorities
     * @param roles
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthority(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (Role role : roles) {
            log.info("Role name: " + role);
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        List<GrantedAuthority> result = new ArrayList<>(authorities);

        return result;
    }
}
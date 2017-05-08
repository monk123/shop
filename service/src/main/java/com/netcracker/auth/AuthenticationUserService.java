package com.netcracker.auth;

import com.netcracker.pojo.User;

/**
 * interface for authentication user
 *
 * @author Ayupov Vadim
 */

public interface AuthenticationUserService {

    void save(User user);

    User findByUsername(String username);
}

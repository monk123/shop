package com.netcracker.service;

import com.netcracker.pojo.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<User, Long> {

    List<User> paginationUser(int firstValue, int maxValue);

    User loadByUsername(String username);

    void editUserInformation(User user);

    User loadUserByEmail(String email);
}

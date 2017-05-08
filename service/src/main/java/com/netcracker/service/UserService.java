package com.netcracker.service;

import com.netcracker.pojo.User;

import java.util.List;

public interface UserService extends BaseService<User, Long> {

    List<User> paginationUser(int firstValue, int maxValue);

    User loadByUsername(String username);
}

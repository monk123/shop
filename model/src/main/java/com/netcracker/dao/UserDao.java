package com.netcracker.dao;

import com.netcracker.pojo.User;

import java.util.List;

public interface UserDao extends BaseDao<User, Long> {

    List<User> paginationUser(int firstValue, int maxValue);

    User loadByUsername(String username);
}

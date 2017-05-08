package com.netcracker.service.impl;

import com.netcracker.dao.RoleDao;
import com.netcracker.pojo.Role;
import com.netcracker.service.RoleService;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}

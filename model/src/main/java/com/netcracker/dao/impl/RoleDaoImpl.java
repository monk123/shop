package com.netcracker.dao.impl;

import com.netcracker.dao.RoleDao;
import com.netcracker.pojo.Role;
import com.netcracker.pojo.User;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

/**
 * Implementation of {@link RoleDao} interface
 * Extends of {@link BaseDaoImpl} class
 *
 * @author Ayupov Vadim
 * @version 1.0
 */

@Log
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {
}

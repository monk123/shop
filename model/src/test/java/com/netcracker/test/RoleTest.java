package com.netcracker.test;

import com.netcracker.dao.RoleDao;
import com.netcracker.pojo.Role;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/model-spring-test.xml")
@Rollback
@Transactional
public class RoleTest {

    @Autowired
    private RoleDao roleDao;

    @Test
    public void testSaveRole() {
        int size = roleDao.getAllEntities().size();
        Role role = new Role();
        role.setRoleName("role name test");

        roleDao.save(role);

        assertTrue(size < roleDao.getAllEntities().size());
    }

    @Test
    public void testUpdateRole() {
        Role role = new Role();
        role.setRoleName("role name test");

        roleDao.save(role);

        role.setRoleName("role name update test");

        Role roleTest = roleDao.getEntityById(role.getId());
        roleDao.update(role);

        assertEquals("role name update test", roleTest.getRoleName());
    }

    @Test
    public void testGetRoleById() {
        Role role = new Role();
        role.setRoleName("role name test");

        roleDao.save(role);

        Role roleTest = roleDao.getEntityById(role.getId());

        assertEquals(role, roleTest);
    }

    @Test
    public void testGetAllRoles() {
        //assertEquals(0, roleDao.getAllEntities().size());

        List<Role> roles = Arrays.asList(
                new Role("role 1"),
                new Role("role 2"),
                new Role("role 3"),
                new Role("role 4")
        );

        roles.forEach(role -> roleDao.save(role));

        List<Role> rolesTest = roleDao.getAllEntities();
        assertEquals(6, rolesTest.size());

        rolesTest.forEach(role -> assertTrue(rolesTest.contains(role)));
    }

    @Test
    public void testDeleteRole() {
        Role role = new Role();
        role.setRoleName("role name test");

        roleDao.save(role);

        assertEquals(role, roleDao.getEntityById(role.getId()));

        roleDao.delete(role.getId());

        assertNull(roleDao.getEntityById(role.getId()));
    }
}

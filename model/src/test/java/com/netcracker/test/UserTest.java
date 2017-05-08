package com.netcracker.test;

import com.netcracker.dao.AddressDao;
import com.netcracker.dao.UserDao;
import com.netcracker.pojo.Address;
import com.netcracker.pojo.User;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/model-spring-test.xml")
@Transactional
public class UserTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AddressDao addressDao;

    @Test
    public void testSaveUser() {
        int size = userDao.getAllEntities().size();

        User user = new User();
        user.setUsername("User name save test");
        user.setLastName("User lastName save test");
        user.setEmail("User email save test");
        user.setPassword("user password save test");
        user.setPhone("80297474128");

        log.info("User: " + user);

        userDao.save(user);

        assertTrue(size < userDao.getAllEntities().size());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("User name update test");
        user.setLastName("User lastName update test");
        user.setEmail("User email update test");
        user.setPassword("user password update test");
        user.setPhone("80297474128");

        userDao.save(user);

        user.setUsername("User name update test after save");

        User userTest = userDao.getEntityById(user.getId());
        userDao.update(user);

        assertEquals("User name update test after save", userTest.getUsername());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setUsername("User name findCategoryByCategoryName test");
        user.setLastName("User lastName findCategoryByCategoryName test");
        user.setEmail("User email findCategoryByCategoryName test");
        user.setPassword("user password findCategoryByCategoryName test");
        user.setPhone("80297474128");

        userDao.save(user);

        User userTest = userDao.getEntityById(user.getId());
        assertEquals(user, userTest);
    }

    @Test
    public void testGetAllUsers() {
        assertEquals(0, userDao.getAllEntities().size());

        List<User> users = Arrays.asList(
                new User("User 1", "User 1 last name", "email@_1", "password_1", "phone_1"),
                new User("User 2", "User 2 last name", "email@_2", "password_2", "phone_2"),
                new User("User 3", "User 3 last name", "email@_3", "password_3", "phone_3"),
                new User("User 4", "User 4 last name", "email@_4", "password_4", "phone_4")
        );

        users.forEach(user -> userDao.save(user));

        List<User> usersTest = userDao.getAllEntities();

        assertEquals(4, userDao.getAllEntities().size());

        usersTest.forEach(user -> assertTrue(users.contains(user)));
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("User name delete test");
        user.setLastName("User lastName delete test");
        user.setEmail("User email delete test");
        user.setPassword("user password delete test");
        user.setPhone("80297474128");

        userDao.save(user);

        assertEquals(user, userDao.getEntityById(user.getId()));

        userDao.delete(user.getId());

        assertNull(userDao.getEntityById(user.getId()));
    }

    @Test
    public void testFindUser() {
        User user = new User();
        user.setUsername("User name find test");
        user.setLastName("User lastName find test");
        user.setEmail("User email find test");
        user.setPassword("user password find test");
        user.setPhone("80297474128");

        userDao.save(user);

        User userTest = userDao.loadByUsername("User name find test");

        assertEquals(user, userTest);
    }

    @Test
    public void testPaginationUser() {
        List<User> users = Arrays.asList(
                new User("User 1", "User 1 last name", "email@_1", "password_1", "phone_1"),
                new User("User 2", "User 2 last name", "email@_2", "password_2", "phone_2"),
                new User("User 3", "User 3 last name", "email@_3", "password_3", "phone_3"),
                new User("User 4", "User 4 last name", "email@_4", "password_4", "phone_4"),
                new User("User 5", "User 5 last name", "email@_5", "password_5", "phone_5"),
                new User("User 6", "User 6 last name", "email@_6", "password_6", "phone_6"),
                new User("User 7", "User 7 last name", "email@_7", "password_7", "phone_7"),
                new User("User 8", "User 8 last name", "email@_8", "password_8", "phone_8")
        );

        users.forEach(user -> userDao.save(user));

        List<User> usersTest = userDao.paginationUser(0, 5);

        usersTest.forEach(user -> log.info("Products list:" + user));

        assertEquals(5, usersTest.size());
    }
}

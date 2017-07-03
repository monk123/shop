package com.netcracker.dao.impl;

import com.netcracker.dao.UserDao;
import com.netcracker.pojo.User;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.util.List;

/**
 * Implementation of {@link UserDao} interface
 * Extends of {@link BaseDaoImpl} class
 *
 * @author Ayupov Vadim
 * @version 1.0
 */

@Log
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    @Override
    public User loadByUsername(String username) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root).where(builder.equal(root.get("username"), username));
        List<User> users = session.createQuery(criteria).list();
        if (users != null && !users.isEmpty()) {
            log.info("User successfully find: " + users.get(0));
            return users.get(0);
        }

        return null;
    }

    @Override
    public User loadUserByEmail(String email) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root).where(builder.equal(root.get("email"), email));
        List<User> users = session.createQuery(criteria).list();

        if (users != null && !users.isEmpty()) {
            log.info("User successfully find by email: " + users.get(0));
            return users.get(0);
        }

        return null;
    }

    @Override
    public List<User> paginationUser(int firstValue, int maxValue) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);

        List<User> users = session.createQuery(criteria)
                .setFirstResult(firstValue - 1)
                .setMaxResults(maxValue)
                .list();

        if (users != null && !users.isEmpty()) {
            users.forEach(user -> log.info("User list: " + user));
            return users;
        }

        return null;
    }
}

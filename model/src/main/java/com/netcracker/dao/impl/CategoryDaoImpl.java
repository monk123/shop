package com.netcracker.dao.impl;

import com.netcracker.dao.CategoryDao;
import com.netcracker.pojo.Category;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Implementation of {@link CategoryDao} interface
 * Extends of {@link BaseDaoImpl} class
 *
 * @author Ayupov Vadim
 * @version 1.0
 */

@Log
@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category, Long> implements CategoryDao {

    @Override
    public Category findCategoryByCategoryName(String categoryName) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        Root<Category> root = criteria.from(Category.class);
        criteria.select(root).where(builder.equal(root.get("categoryName"), categoryName));
        List<Category> categories = session.createQuery(criteria).list();

        if (categories != null && !categories.isEmpty()) {
            categories.forEach(category -> log.info(category.getClass() + " list: " + category));
            return categories.get(0);
        }

        return null;
    }

    @Override
    public List<String> getCategoryByUniqueName() {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<String> criteria = builder.createQuery(String.class);
        Root<Category> root = criteria.from(Category.class);
        criteria.select(root.get("categoryName")).distinct(true);
        return session.createQuery(criteria).list();
    }
}

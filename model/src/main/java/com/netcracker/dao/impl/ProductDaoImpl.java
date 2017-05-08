package com.netcracker.dao.impl;

import com.netcracker.dao.ProductDao;
import com.netcracker.pojo.Product;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Implementation of {@link ProductDao} interface
 * Extends of {@link BaseDaoImpl} class
 *
 * @author Ayupov Vadim
 * @version 1.0
 */

@Log
@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product, Long> implements ProductDao {

    /**
     * shows a limited number of products depending on the parameters
     * @param firstValue
     * @param maxValue
     * @return
     */
    @Override
    public List<Product> paginationProduct(int firstValue, int maxValue) {
        Session session = currentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);

        return session.createQuery(criteria)
                .setFirstResult(firstValue)
                .setMaxResults(maxValue)
                .list();
    }

    /**
     * this method is needed to find product by name
     * @param name
     * @return
     */
    @Override
    public Product findProduct(String name) {
        Session session = currentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root).where(builder.equal(root.get("name"), name));
        List<Product> products = session.createQuery(criteria).list();

        if (!products.isEmpty()) {
            log.info("Product successfully find: " + products.get(0));
            return products.get(0);
        }

        return null;
    }

    @Override
    public List<Product> getProductByCategoryName(String name, int val1, int val2) {
        Session session = currentSession();
        Query query = session
                .createQuery("select p from Product p where category.categoryName=:name")
                .setParameter("name", name)
                .setFirstResult(val1).setMaxResults(val2);
        return (List<Product>) query.getResultList();
    }
}

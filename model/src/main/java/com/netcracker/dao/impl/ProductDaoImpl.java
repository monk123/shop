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
     *
     * @param firstValue
     * @param maxValue
     * @return
     */
    @Override
    public List<Product> paginationProduct(int firstValue, int maxValue) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);
        List<Product> products = session.createQuery(criteria)
                .setFirstResult(firstValue)
                .setMaxResults(maxValue)
                .list();

        products.forEach(product -> log.info("Pagination product: " + product));

        return products;
    }

    /**
     * this method is needed to find product by name
     *
     * @param name
     * @return
     */
    @Override
    public Product findProduct(String name) {
        Session session = getCurrentSession();
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
    public List<Product> desc(int first, int second) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root).orderBy(builder.desc(root.get("price")));
        List<Product> products = session.createQuery(criteria)
                .setFirstResult(first)
                .setMaxResults(second)
                .list();
        products.forEach(product -> log.info("Products desc: " + product));
        return products;
    }

    @Override
    public List<Product> asc(int first, int second) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root).orderBy(builder.asc(root.get("price")));
        List<Product> products = session.createQuery(criteria)
                .setFirstResult(first)
                .setMaxResults(second)
                .list();
        products.forEach(product -> log.info("Products asc: " + product));
        return products;
    }

    @Override
    public List<Product> between(int first, int second, int startPrice, int finishPrice) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        builder.between(root.get("price"), startPrice, finishPrice);
        List<Product> products = session.createQuery(criteria)
                .setFirstResult(first)
                .setMaxResults(second)
                .list();
        products.forEach(product -> log.info("Products between: " + product));
        return products;
    }
}

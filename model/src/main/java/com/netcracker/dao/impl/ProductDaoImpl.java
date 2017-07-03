package com.netcracker.dao.impl;

import com.netcracker.dao.ProductDao;
import com.netcracker.pojo.Category;
import com.netcracker.pojo.Product;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Stream;

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

    @Override
    public List<Product> paginationProduct(int firstValue, int maxValue) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);
        List<Product> products = session.createQuery(criteria)
                .setFirstResult(firstValue-1)
                .setMaxResults(maxValue)
                .list();

        products.forEach(product -> log.info("Pagination product: " + product));

        return products;
    }

    @Override
    public List<Product> getProductByCategoryName(String name, int firstValue, int maxValue) {
        Session session = getCurrentSession();
        Query query = session
                .createQuery("SELECT p FROM Product " +
                        "AS p WHERE p.category.categoryName=:name")
                .setParameter("name", name)
                .setFirstResult(firstValue - 1).setMaxResults(maxValue);
        List<Product> products = query.getResultList();
        products.forEach(product ->
                log.info("List product by category name: " + product.getCategory().getCategoryName()));

        return products;
    }

    @Override
    public List<Product> getProductByPrice(double priceFrom, double priceTo,
                                           int firstValue, int secondValue) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);

        criteria.where(
                builder.ge(root.get("price"), priceFrom),
                builder.le(root.get("price"), priceTo)
        ).orderBy(
                builder.asc(root.get("price")),
                builder.desc(root.get("price"))
        );

        return session.createQuery(criteria).
                setFirstResult(firstValue - 1).
                setMaxResults(secondValue).
                list();
    }

    @Override
    public List<Product> getProductByPriceAndCategory(String category, double priceFrom, double priceTo,
                                                      int firstValue, int secondValue) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);
        criteria.where(
                builder.equal(root.get("product.categoryName"), category),
                builder.ge(root.get("price"), priceFrom),
                builder.le(root.get("price"), priceTo)
        );

        return session.createQuery(criteria).setMaxResults(secondValue).setFirstResult(firstValue - 1).list();
    }

    @Override
    public List<Product> findProduct(String name, int page, int total) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        Predicate predicate = builder.like(root.<String>get("name"), name);
        criteria.where(predicate);
        criteria.select(root);
        TypedQuery query = session.createQuery(criteria).setFirstResult(page - 1).setMaxResults(total);
        List<Product> result = query.getResultList();
        result.forEach(product -> log.info("Product find: " + product));
        return result;
    }

    @Override
    public List<Product> getProductByPriceSize(double priceFrom, double priceTo) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);

        criteria.where(
                builder.ge(root.get("price"), priceFrom),
                builder.le(root.get("price"), priceTo)
        );

        return session.createQuery(criteria).list();
    }

    @Override
    public List<Product> findProductSize(String name) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        Predicate predicate = builder.like(root.get("name"), "%" + name + "%");
        criteria.where(predicate);
        criteria.select(root);
        TypedQuery query = session.createQuery(criteria);
        List<Product> result = query.getResultList();
        result.forEach(product -> log.info("Product find: " + product));
        return result;
    }

    @Override
    public List<Product> getProductByOrderId(Long id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select PRODUCT_ID from orders_has_products where ORDER_ID=:id")
                .setParameter("ORDER_ID", id);
        List<Product> products = query.getResultList();
        return products;
    }

    @Override
    public List<Product> getProductByCategoryNameSize(String name) {
        Session session = getCurrentSession();

        Query query = session.
                createQuery("SELECT p FROM Product AS p WHERE p.category.categoryName=:name").
                setParameter("name", name);

        List<Product> products = query.getResultList();

        products.forEach(product ->
                log.info("List product by category name: " + product.getCategory().getCategoryName()));

        return products;
    }

}

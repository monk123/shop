package com.netcracker.dao.impl;

import com.netcracker.dao.CommentDao;
import com.netcracker.pojo.Comment;
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
 * Implements of {@link CommentDao} interface and extends {@link BaseDaoImpl} class
 *
 * @author Ayupov Vadim
 */

@Repository
@Log
public class CommentDaoImpl extends BaseDaoImpl<Comment, Long> implements CommentDao {

    private static final String SELECT_COMMENT_BY_PRODUCT_ID = "SELECT c FROM Comment AS c WHERE c.product.id=:id";

    @Override
    public List<Comment> getAllCommentByProductSQL(Long id) {
        Session session = getCurrentSession();
        Query query = session.createQuery(SELECT_COMMENT_BY_PRODUCT_ID).setParameter("id", id);
        List<Comment> comments = query.getResultList();
        return comments;
    }
}

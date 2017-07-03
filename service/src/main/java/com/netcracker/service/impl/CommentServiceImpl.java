package com.netcracker.service.impl;

import com.netcracker.auth.AuthenticationSecurityService;
import com.netcracker.dao.CommentDao;
import com.netcracker.dao.ProductDao;
import com.netcracker.dao.UserDao;
import com.netcracker.pojo.Comment;
import com.netcracker.pojo.Product;
import com.netcracker.pojo.User;
import com.netcracker.service.CommentService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Implementation of {@link CommentService} and extends {@link BaseServiceImpl}
 *
 * @author Ayupov Vadim
 */

@Log
@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment, Long> implements CommentService {
    private CommentDao commentDao;
    private UserDao userDao;
    private AuthenticationSecurityService authenticationSecurityService;
    private ProductDao productDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao,
                              UserDao userDao,
                              AuthenticationSecurityService authenticationSecurityService,
                              ProductDao productDao) {
        this.commentDao = commentDao;
        this.userDao = userDao;
        this.authenticationSecurityService = authenticationSecurityService;
        this.productDao = productDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllCommentsByProductSQL(Long id) {
        return commentDao.getAllCommentByProductSQL(id);
    }

    @Override
    @Transactional
    public void addComment(Comment comment) {
        String username = authenticationSecurityService.findLoggedInUsername();
        User user = userDao.loadByUsername(username);
        comment.setUser(user);
        comment.setDate(new Date(System.currentTimeMillis()));
        commentDao.save(comment);
    }
}

package com.netcracker.dao;


import com.netcracker.pojo.Comment;

import java.util.List;

public interface CommentDao extends BaseDao<Comment, Long> {

    List<Comment> getAllCommentByProductSQL(Long id);
}

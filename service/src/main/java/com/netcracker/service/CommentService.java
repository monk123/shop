package com.netcracker.service;

import com.netcracker.pojo.Comment;

import java.util.List;

public interface CommentService extends BaseService<Comment, Long> {

    List<Comment> getAllCommentsByProductSQL(Long id);

    void addComment(Comment comment);
}

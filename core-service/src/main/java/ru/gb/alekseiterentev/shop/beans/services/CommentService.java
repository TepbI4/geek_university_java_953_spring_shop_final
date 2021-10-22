package ru.gb.alekseiterentev.shop.beans.services;

import ru.gb.alekseiterentev.shop.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getProductComments(Long id);
    void createProductComment(Comment productComment);
}

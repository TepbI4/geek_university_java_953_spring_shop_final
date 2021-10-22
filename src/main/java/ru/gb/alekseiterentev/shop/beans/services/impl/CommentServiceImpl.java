package ru.gb.alekseiterentev.shop.beans.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.alekseiterentev.shop.beans.repositories.CommentRepository;
import ru.gb.alekseiterentev.shop.beans.services.CommentService;
import ru.gb.alekseiterentev.shop.model.Comment;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getProductComments(Long id) {
        return commentRepository.findAllByProductId(id);
    }

    @Override
    public void createProductComment(Comment productComment) {
        commentRepository.save(productComment);
    }
}

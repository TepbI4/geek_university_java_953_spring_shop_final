package ru.gb.alekseiterentev.shop.beans.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.alekseiterentev.shop.beans.services.CommentService;
import ru.gb.alekseiterentev.shop.beans.services.ProductService;
import ru.gb.alekseiterentev.shop.exceptions.ProductNotFoundException;
import ru.gb.alekseiterentev.shop.model.Comment;
import ru.gb.alekseiterentev.shop.model.dto.CommentDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comments")
public class CommentController {

    private final CommentService commentService;
    private final ProductService productService;

    @GetMapping("/{productId}")
    public List<CommentDto> getProductComments(@PathVariable Long productId) {
        return commentService.getProductComments(productId).stream().map(CommentDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public void createComment(@RequestBody CommentDto commentDto) {
        Comment productComment = new Comment();
        productComment.setProduct(productService.findById(commentDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + commentDto.getProductId() + " not found")));
        productComment.setComment(commentDto.getComment());
        commentService.createProductComment(productComment);
    }
}

package ru.gb.alekseiterentev.shop.beans.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.alekseiterentev.shop.beans.services.CommentService;
import ru.gb.alekseiterentev.shop.beans.services.OrderService;
import ru.gb.alekseiterentev.shop.beans.services.ProductService;
import ru.gb.alekseiterentev.shop.exceptions.ResourceNotFoundException;
import ru.gb.alekseiterentev.shop.model.Comment;
import ru.gb.alekseiterentev.shop.model.dto.CommentDto;
import ru.gb.alekseiterentev.shop.utils.Converter;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comments")
public class CommentController {

    private final CommentService commentService;
    private final ProductService productService;
    private final OrderService orderService;
    private final Converter converter;

    @GetMapping("/{productId}")
    public List<CommentDto> getProductComments(@PathVariable Long productId) {
        return commentService.getProductComments(productId).stream().map(converter::commentToDto).collect(Collectors.toList());
    }

    @PostMapping
    public void createComment(@RequestBody CommentDto commentDto) {
        Comment productComment = new Comment();
        productComment.setProduct(productService.findById(commentDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + commentDto.getProductId() + " not found")));
        productComment.setComment(commentDto.getComment());
        commentService.createProductComment(productComment);
    }

    @GetMapping("/{productId}/check")
    public boolean checkThatUserOrderedProduct(Principal principal, @PathVariable Long productId) {
        return orderService.checkThatUserOrderedProduct(principal, productId);
    }
}

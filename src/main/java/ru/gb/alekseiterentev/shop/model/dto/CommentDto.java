package ru.gb.alekseiterentev.shop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.alekseiterentev.shop.model.Comment;

@Data
@NoArgsConstructor
public class CommentDto {

    private Long productId;
    private String comment;

    public CommentDto(Comment comment) {
        this.productId = comment.getProduct().getId();
        this.comment = comment.getComment();
    }
}

package ru.gb.alekseiterentev.shop.model.dto;


public class CommentDto {

    private Long productId;
    private String comment;

    public CommentDto() {
    }

    public CommentDto(Long productId, String comment) {
        this.productId = productId;
        this.comment = comment;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

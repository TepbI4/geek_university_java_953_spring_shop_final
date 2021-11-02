package ru.gb.alekseiterentev.shop.utils;

import org.springframework.stereotype.Component;
import ru.gb.alekseiterentev.shop.model.Comment;
import ru.gb.alekseiterentev.shop.model.Order;
import ru.gb.alekseiterentev.shop.model.OrderItem;
import ru.gb.alekseiterentev.shop.model.Product;
import ru.gb.alekseiterentev.shop.model.dto.CommentDto;
import ru.gb.alekseiterentev.shop.model.dto.OrderDto;
import ru.gb.alekseiterentev.shop.model.dto.OrderItemDto;
import ru.gb.alekseiterentev.shop.model.dto.ProductDto;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class Converter {

    public ProductDto productToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

    public OrderItemDto orderItemToDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getProduct().getId()
                , orderItem.getProduct().getTitle()
                , orderItem.getPrice()
                , orderItem.getQuantity()
                , orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))
        );
    }

    public OrderDto orderToDto(Order order) {
        return new OrderDto(order.getId(), order.getStatus().name(), order.getOrderItems().stream().map(this::orderItemToDto).collect(Collectors.toList()), order.getAddress(), order.getPhone(), order.getTotal());
    }

    public CommentDto commentToDto(Comment comment) {
        return new CommentDto(comment.getProduct().getId(), comment.getComment());
    }
}

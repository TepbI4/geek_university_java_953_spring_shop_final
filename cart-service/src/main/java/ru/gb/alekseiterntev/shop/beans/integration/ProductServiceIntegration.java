package ru.gb.alekseiterntev.shop.beans.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.alekseiterentev.shop.model.dto.ProductDto;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final WebClient productServiceWebClient;

    public ProductDto getProductById(Long productId) {
        ProductDto productDto = productServiceWebClient.get()
                .uri("/api/v1/products/" + productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
        return productDto;
    }
}

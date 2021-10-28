package ru.gb.alekseiterentev.shop.beans.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.alekseiterentev.shop.model.dto.CartDto;

import java.security.Principal;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;

    public CartDto getUserCartDto(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("ERROR!!!");
        }

        CartDto cartDto = cartServiceWebClient.get()
                .uri("/api/v1/cart/" + principal.getName())
                .header("username", principal.getName())
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();

        return cartDto;
    }

    public void clear(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("ERROR!!!");
        }

        cartServiceWebClient.get()
                .uri("/api/v1/cart/"+principal.getName() + "/clear")
                .header("username", principal.getName())
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}

package ru.gb.alekseiterentev.shop.beans.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.alekseiterentev.shop.beans.services.OrderService;
import ru.gb.alekseiterentev.shop.exceptions.ExceptionMessage;
import ru.gb.alekseiterentev.shop.model.dto.OrderDetailsDto;
import ru.gb.alekseiterentev.shop.utils.Converter;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final Converter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto, Principal principal) {
        orderService.createOrder(orderDetailsDto, principal);
    }

    @GetMapping
    public ResponseEntity<?> getOrdersForCurrentUser(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(new ExceptionMessage("Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(orderService.findAllByUsername(principal.getName())
                .stream().map(converter::orderToDto).collect(Collectors.toList()), HttpStatus.OK);
    }
}

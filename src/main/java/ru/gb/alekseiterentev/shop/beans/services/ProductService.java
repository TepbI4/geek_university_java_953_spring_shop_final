package ru.gb.alekseiterentev.shop.beans.services;

import ru.gb.alekseiterentev.shop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll(Integer minPrice, Integer maxPrice);
    Optional<Product> findById(Long id);
    Product save(Product product);
    void delete(Long id);
}

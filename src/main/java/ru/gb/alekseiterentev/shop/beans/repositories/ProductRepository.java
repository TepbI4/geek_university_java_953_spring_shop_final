package ru.gb.alekseiterentev.shop.beans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.alekseiterentev.shop.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceGreaterThan(Integer minPrice);
    List<Product> findByPriceLessThan(Integer maxPrice);
    List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);
}

package ru.gb.alekseiterentev.shop.beans.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.gb.alekseiterentev.shop.beans.repositories.ProductRepository;
import ru.gb.alekseiterentev.shop.beans.services.ProductService;
import ru.gb.alekseiterentev.shop.model.Product;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> findAll(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> filterByPrice(Integer minPrice, Integer maxPrice) {
        if (Objects.nonNull(minPrice) && Objects.nonNull(maxPrice)) {
            return productRepository.findByPriceBetween(minPrice, maxPrice);
        }

        if (Objects.nonNull(minPrice)) {
            return productRepository.findByPriceGreaterThan(minPrice);
        }

        if (Objects.nonNull(maxPrice)) {
            return productRepository.findByPriceLessThan(maxPrice);
        }

        return productRepository.findAll();
    }
}

package ru.gb.alekseiterentev.spring_shop_final.beans.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.alekseiterentev.spring_shop_final.beans.services.ProductService;
import ru.gb.alekseiterentev.spring_shop_final.model.Product;
import ru.gb.alekseiterentev.spring_shop_final.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts(@RequestParam(required = false) Integer minPrice,
                                            @RequestParam(required = false) Integer maxPrice) {
        return productService.findAll(minPrice, maxPrice).stream().map(ProductDto::new).collect(toList());
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findById(id).map(ProductDto::new).orElse(null);
    }

    @PostMapping("/create")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return Optional.of(productService.save(product)).map(ProductDto::new).orElse(null);
    }

    @GetMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}

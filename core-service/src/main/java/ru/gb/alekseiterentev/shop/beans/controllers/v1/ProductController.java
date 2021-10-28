package ru.gb.alekseiterentev.shop.beans.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.alekseiterentev.shop.beans.services.ProductService;
import ru.gb.alekseiterentev.shop.exceptions.ProductNotFoundException;
import ru.gb.alekseiterentev.shop.model.Product;
import ru.gb.alekseiterentev.shop.model.dto.ProductDto;
import ru.gb.alekseiterentev.shop.utils.Converter;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final Converter converter;

    @GetMapping
    public Page<ProductDto> findAllProducts(@RequestParam(required = true) int page) {
        return productService.findAll(page - 1, 10).map(converter::productToDto);
    }

    @GetMapping("/filter")
    public List<ProductDto> filterByPrice(@RequestParam(required = false) Integer minPrice,
                                       @RequestParam(required = false) Integer maxPrice) {
        return productService.filterByPrice(minPrice, maxPrice).stream().map(converter::productToDto).collect(toList());
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productService.findById(id).map(converter::productToDto)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found"));
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return Optional.of(productService.save(product)).map(converter::productToDto).orElse(null);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return Optional.of(productService.save(product)).map(converter::productToDto).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}

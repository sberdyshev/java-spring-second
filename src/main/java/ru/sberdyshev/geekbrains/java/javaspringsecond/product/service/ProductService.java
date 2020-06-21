package ru.sberdyshev.geekbrains.java.javaspringsecond.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain.Product;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.dto.ProductDto;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Page<ProductDto> getAllProductsPageable(Pageable page);

    ProductDto getProduct(UUID productId);
    ProductDto getProduct(String productName);
}

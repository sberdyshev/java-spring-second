package ru.sberdyshev.geekbrains.java.javaspringsecond.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Page<Product> getAllProductsPageable(Pageable page);

    Optional<Product> getProductById(UUID id);
}

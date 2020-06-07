package ru.sberdyshev.geekbrains.java.javaspringsecond.product.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain.Product;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.exception.ProductNotFountException;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.repository.ProductRepository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.service.ProductService;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getAllProductsPageable(Pageable page) {
        log.debug("getAllProducts() - Start with args: page={}", page);
        Page<Product> returnProductPage = productRepository.findAll(page);
        log.debug("getAllProducts() - Return value: Page<Product>={}", returnProductPage);
        return returnProductPage;
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        log.debug("getProduct() - Start with args: id={}", id);
        Optional<Product> resultProduct = productRepository.findById(id);
        if (!resultProduct.isPresent()) {
            log.warn("getProduct() - Product with id={} not found", id);
            throw new ProductNotFountException("Product with id=" + id + " is not found");
        }
        log.debug("getProduct() - Return value: Optional<Product>={}", resultProduct);
        return resultProduct;
    }
}

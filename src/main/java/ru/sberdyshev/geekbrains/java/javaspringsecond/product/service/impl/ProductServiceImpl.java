package ru.sberdyshev.geekbrains.java.javaspringsecond.product.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain.Product;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.dto.ProductDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.exception.ProductNotFountException;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.repository.ProductRepository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.service.ProductService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ProductDto> getAllProductsPageable(Pageable page) {
        log.debug("getAllProducts() - Start with args: page={}", page);
        Page<Product> productPage = productRepository.findAll(page);
        List<Product> productList = productPage.getContent();
        Type listProductDtoType = new TypeToken<List<ProductDto>>() {
        }.getType();
        List<ProductDto> productDtoList = modelMapper.map(productList, listProductDtoType);
        Page<ProductDto> productDtoPage = new PageImpl<>(productDtoList);
        log.debug("getAllProducts() - Return value: Page<ProductDto>={}", productDtoPage);
        return productDtoPage;
    }

    @Override
    public Optional<ProductDto> getProductById(UUID id) {
        log.debug("getProduct() - Start with args: id={}", id);
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            log.warn("getProduct() - Product with id={} not found", id);
            throw new ProductNotFountException("Product with id=" + id + " is not found");
        }
        Optional<ProductDto> optionalProductDto = Optional.ofNullable(modelMapper.map(optionalProduct.get(), ProductDto.class));
        log.debug("getProduct() - Return value: Optional<ProductDto>={}", optionalProductDto);
        return optionalProductDto;
    }
}

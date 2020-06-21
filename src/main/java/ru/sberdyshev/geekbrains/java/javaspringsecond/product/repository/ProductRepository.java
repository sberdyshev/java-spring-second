package ru.sberdyshev.geekbrains.java.javaspringsecond.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain.Product;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String productName);
}

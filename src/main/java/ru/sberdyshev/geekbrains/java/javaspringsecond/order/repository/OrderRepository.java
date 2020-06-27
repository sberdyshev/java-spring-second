package ru.sberdyshev.geekbrains.java.javaspringsecond.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.Order;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    Page<Order> findAllByUserId(UUID userId, Pageable page);
    Optional<Order> findByIdAndUserId(UUID orderId, UUID userId);
}

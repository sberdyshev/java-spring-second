package ru.sberdyshev.geekbrains.java.javaspringsecond.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.Status;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StatusReposiroty extends JpaRepository<Status, UUID> {
    Optional<Status> findByCode(String statusCode);
}
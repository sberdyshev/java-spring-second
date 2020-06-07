package ru.sberdyshev.geekbrains.java.javaspringsecond.security.repository;


import org.springframework.data.repository.CrudRepository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.security.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findOneByUserName(String userName);
}

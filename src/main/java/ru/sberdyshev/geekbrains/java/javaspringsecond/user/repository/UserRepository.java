package ru.sberdyshev.geekbrains.java.javaspringsecond.user.repository;


import org.springframework.data.repository.CrudRepository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findOneByUserName(String userName);
}

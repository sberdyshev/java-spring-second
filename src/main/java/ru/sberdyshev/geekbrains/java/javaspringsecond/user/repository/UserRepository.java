package ru.sberdyshev.geekbrains.java.javaspringsecond.user.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.domain.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findOneByUserName(String userName);
}

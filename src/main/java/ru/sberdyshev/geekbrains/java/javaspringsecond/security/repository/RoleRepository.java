package ru.sberdyshev.geekbrains.java.javaspringsecond.security.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.security.domain.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
    Optional<Role> findOneByName(String theRoleName);
}

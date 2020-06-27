package ru.sberdyshev.geekbrains.java.javaspringsecond.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.domain.Role;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {
    Optional<Role> findOneByName(String theRoleName);
}

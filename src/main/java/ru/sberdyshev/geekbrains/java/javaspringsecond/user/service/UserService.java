package ru.sberdyshev.geekbrains.java.javaspringsecond.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.dto.UserDto;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String userName);

    UserDto getUser(String userName);

    UserDto getUser(UUID userId);

    UserDto getCurrentUser();
}

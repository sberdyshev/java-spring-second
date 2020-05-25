package ru.sberdyshev.geekbrains.java.javaspringsecond.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.sberdyshev.geekbrains.java.javaspringsecond.security.domain.Role;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Collection<Role> roles;

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' + ", " +
                "userName='" + userName + '\'' + ", " +
                "password='" + "*********" + '\'' + ", " +
                "firstName='" + firstName + '\'' + ", " +
                "lastName='" + lastName + '\'' + ", " +
                "roles=" + roles + '}';
    }
}

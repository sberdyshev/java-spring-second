package ru.sberdyshev.geekbrains.java.javaspringsecond.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.sberdyshev.geekbrains.java.javaspringsecond.security.domain.Role;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private UUID id;

    @NotNull
    @Length(max = 255, message = "The \"userName\" field should be less than 255 characters")
    private String userName;

    @NotNull
    @Length(max = 255, message = "The \"password\" field should be less than 255 characters")
    private String password;

    @NotNull
    @Length(max = 255, message = "The \"firstName\" field should be less than 255 characters")
    private String firstName;

    @NotNull
    @Length(max = 255, message = "The \"lastName\" field should be less than 255 characters")
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

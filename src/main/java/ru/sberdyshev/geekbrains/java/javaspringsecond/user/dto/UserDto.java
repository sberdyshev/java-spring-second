package ru.sberdyshev.geekbrains.java.javaspringsecond.user.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.domain.Role;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Length(max = 255, message = "The \"matchingPassword\" field should be less than 255 characters")
    private String matchingPassword;

    private Collection<Role> roles;

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String objectAsJson = mapper.writeValueAsString(this);
            return objectAsJson;
        } catch (JsonProcessingException jsonProcessingException) {
            return this.getClass().getName();
        }
    }
}

package ru.sberdyshev.geekbrains.java.javaspringsecond.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RoleDto {

    private UUID id;

    @NotNull
    @Length(max = 255, message = "The \"name\" field should be less than 255 characters")
    private String name;

    @Override
    public String toString() {
        return "RoleDto{" +
                "id='" + id + '\'' + ", " +
                "name='" + name + '\'' + '}';
    }
}

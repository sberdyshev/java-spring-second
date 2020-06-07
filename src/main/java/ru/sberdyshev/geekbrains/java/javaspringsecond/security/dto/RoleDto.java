package ru.sberdyshev.geekbrains.java.javaspringsecond.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RoleDto {
    private UUID id;
    private String name;

    @Override
    public String toString() {
        return "RoleDto{" +
                "id='" + id + '\'' + ", " +
                "name='" + name + '\'' + '}';
    }
}

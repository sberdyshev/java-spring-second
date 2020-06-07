package ru.sberdyshev.geekbrains.java.javaspringsecond.security.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "auth_role")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' + ", " +
                "name='" + name + '\'' + '}';
    }
}

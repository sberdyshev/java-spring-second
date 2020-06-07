package ru.sberdyshev.geekbrains.java.javaspringsecond.security.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "auth_user")
public class User {

    @Id
    @GeneratedValue
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "auth_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(String userName,
                String password,
                String firstName,
                String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String userName,
                String password,
                String firstName,
                String lastName,
                Collection<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' + ", " +
                "userName='" + userName + '\'' + ", " +
                "password='" + "*********" + '\'' + ", " +
                "firstName='" + firstName + '\'' + ", " +
                "lastName='" + lastName + '\'' + ", " +
                "roles=" + roles + '}';
    }
}

package ru.sberdyshev.geekbrains.java.javaspringsecond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class JavaSpringSecondApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringSecondApplication.class, args);
    }
}

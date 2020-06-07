package ru.sberdyshev.geekbrains.java.javaspringsecond.security.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuthExceptionCode {
    ERR_AUTH_USER_AND_PASSWORD_DOES_NOT_MATCH("Пользователь или пароль не совпадают", HttpStatus.UNAUTHORIZED);

    private String description;
    private HttpStatus httpStatus;

    AuthExceptionCode(String description,
                      HttpStatus httpStatus) {
        this.description = description;
        this.httpStatus = httpStatus;
    }
}

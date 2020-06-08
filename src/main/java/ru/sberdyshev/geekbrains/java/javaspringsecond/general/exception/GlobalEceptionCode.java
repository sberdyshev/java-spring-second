package ru.sberdyshev.geekbrains.java.javaspringsecond.general.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GlobalEceptionCode {
    ERR_VALIDATION("Ошибка валидации", HttpStatus.BAD_REQUEST);

    private String description;
    private HttpStatus httpStatus;

    GlobalEceptionCode(String description,
                       HttpStatus httpStatus) {
        this.description = description;
        this.httpStatus = httpStatus;
    }
}

package ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum OrderExceptionCode {
    ERR_ORDER_NOT_FOUND("Заказ не найден.", HttpStatus.NOT_FOUND);

    private String description;
    private HttpStatus httpStatus;

    OrderExceptionCode(String description,
                       HttpStatus httpStatus) {
        this.description = description;
        this.httpStatus = httpStatus;
    }
}

package ru.sberdyshev.geekbrains.java.javaspringsecond.product.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ProductExceptionCode {
    ERR_PRODUCT_NOT_FOUND("Продукт не найден.", HttpStatus.NOT_FOUND);

    private String description;
    private HttpStatus httpStatus;

    ProductExceptionCode(String description,
                         HttpStatus httpStatus) {
        this.description = description;
        this.httpStatus = httpStatus;
    }
}

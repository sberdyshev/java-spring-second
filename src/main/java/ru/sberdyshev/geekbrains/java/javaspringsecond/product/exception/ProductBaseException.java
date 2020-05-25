package ru.sberdyshev.geekbrains.java.javaspringsecond.product.exception;

import org.springframework.http.HttpStatus;

public class ProductBaseException extends RuntimeException {
    private final ProductExceptionCode code;

    public ProductBaseException(ProductExceptionCode exceptionCode,
                                String message) {
        super(message);
        this.code = exceptionCode;
    }

    public String getDescription() {
        return code.getDescription();
    }

    public String getCode() {
        return code.name();
    }

    public HttpStatus getHttpStatus() {
        return code.getHttpStatus();
    }

    @Override
    public String toString() {
        return "ProductBaseException{" +
                "code='" + getCode() + '\'' + ", " +
                "description='" + getDescription() + '\'' + ", " +
                "httpStatus='" + getHttpStatus() + '\'' + '}';
    }
}

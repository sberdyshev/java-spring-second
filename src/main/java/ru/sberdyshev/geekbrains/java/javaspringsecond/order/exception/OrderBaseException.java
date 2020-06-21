package ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception;

import org.springframework.http.HttpStatus;

public class OrderBaseException extends RuntimeException {
    private final OrderExceptionCode code;

    public OrderBaseException(OrderExceptionCode exceptionCode,
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
        return "OrderBaseException{" +
                "code='" + getCode() + '\'' + ", " +
                "description='" + getDescription() + '\'' + ", " +
                "httpStatus='" + getHttpStatus() + '\'' + '}';
    }
}

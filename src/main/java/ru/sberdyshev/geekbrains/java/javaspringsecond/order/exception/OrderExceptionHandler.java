package ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sberdyshev.geekbrains.java.javaspringsecond.general.dto.ErrorDto;

@Slf4j
@ControllerAdvice
public class OrderExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDto> orderNotFountExceptionHandler(OrderNotFountException orderNotFountException) {
        log.debug("orderNotFountExceptionHandler() - Start handling OrderNotFountException. Details: " + orderNotFountException);
        ErrorDto errorMessage = new ErrorDto(
                orderNotFountException.getCode(),
                orderNotFountException.getDescription(),
                orderNotFountException.getCause(),
                orderNotFountException.getStackTrace());
        log.debug("orderNotFountExceptionHandler() - Finished handling OrderNotFountException. Result ErrorDto={}", errorMessage);
        return new ResponseEntity<>(errorMessage, orderNotFountException.getHttpStatus());
    }
}

package ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sberdyshev.geekbrains.java.javaspringsecond.general.dto.ErrorDto;

//todo изменить логирования во всех эксепшн хендлегах
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

    @ExceptionHandler
    public ResponseEntity<ErrorDto> statusNotFountExceptionHandler(StatusNotFoundException statusNotFountException) {
        log.info("statusNotFountExceptionHandler() - Start");
        log.debug("statusNotFountExceptionHandler() - Details: " + statusNotFountException);
        ErrorDto errorMessage = new ErrorDto(
                statusNotFountException.getCode(),
                statusNotFountException.getDescription(),
                statusNotFountException.getCause(),
                statusNotFountException.getStackTrace());
        log.debug("statusNotFountExceptionHandler() - Result ErrorDto={}", errorMessage);
        log.info("statusNotFountExceptionHandler() - Finished");
        return new ResponseEntity<>(errorMessage, statusNotFountException.getHttpStatus());
    }
}

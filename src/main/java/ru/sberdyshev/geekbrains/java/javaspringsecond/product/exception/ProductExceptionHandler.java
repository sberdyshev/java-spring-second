package ru.sberdyshev.geekbrains.java.javaspringsecond.product.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sberdyshev.geekbrains.java.javaspringsecond.general.dto.ErrorDto;

@Slf4j
@ControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDto> productNotFountExceptionHandler(ProductNotFountException productNotFountException) {
        log.debug("productNotFountExceptionHandler() - Start handling ProductNotFountException. Details: " + productNotFountException);
        ErrorDto errorMessage = new ErrorDto(
                productNotFountException.getCode(),
                productNotFountException.getDescription(),
                productNotFountException.getCause(),
                productNotFountException.getStackTrace());
        log.debug("productNotFountExceptionHandler() - Finished handling ProductNotFountException. Result ErrorDto={}", errorMessage);
        return new ResponseEntity<>(errorMessage, productNotFountException.getHttpStatus());
    }
}

package ru.sberdyshev.geekbrains.java.javaspringsecond.general.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sberdyshev.geekbrains.java.javaspringsecond.general.dto.ErrorDto;

import javax.validation.ValidationException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDto> validationExceptionHandler(ValidationException validationException) {
        log.debug("validationExceptionHandler() - Start handling ValidationException. Details: " + validationException);
        ErrorDto errorMessage = new ErrorDto(
                GlobalEceptionCode.ERR_VALIDATION.toString(),
                GlobalEceptionCode.ERR_VALIDATION.getDescription() + ". Details: " + validationException.getMessage(),
                validationException.getCause(),
                validationException.getStackTrace());
        log.debug("validationExceptionHandler() - Finished handling ValidationException. Result ErrorDto={}", errorMessage);
        return new ResponseEntity<>(errorMessage, GlobalEceptionCode.ERR_VALIDATION.getHttpStatus());
    }
}

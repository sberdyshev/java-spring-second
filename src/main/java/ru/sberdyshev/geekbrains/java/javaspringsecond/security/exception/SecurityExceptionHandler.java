package ru.sberdyshev.geekbrains.java.javaspringsecond.security.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sberdyshev.geekbrains.java.javaspringsecond.general.dto.ErrorDto;

@Slf4j
@ControllerAdvice
public class SecurityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDto> usernameNotFoundExceptionHandler(UsernameNotFoundException usernameNotFoundException) {
        //todo ошибка должна не отправлять описание, а редиректить на страницу логина
        log.debug("usernameNotFoundExceptionHandler() - Start handling UsernameNotFoundException. Details: " + usernameNotFoundException);
        ErrorDto errorMessage = new ErrorDto(
                AuthExceptionCode.ERR_AUTH_USER_AND_PASSWORD_DOES_NOT_MATCH.toString(),
                AuthExceptionCode.ERR_AUTH_USER_AND_PASSWORD_DOES_NOT_MATCH.getDescription(),
                usernameNotFoundException.getCause(),
                usernameNotFoundException.getStackTrace());
        log.debug("usernameNotFoundExceptionHandler() - Finished handling UsernameNotFoundException. Result ErrorDto={}", errorMessage);
        return new ResponseEntity<>(errorMessage, AuthExceptionCode.ERR_AUTH_USER_AND_PASSWORD_DOES_NOT_MATCH.getHttpStatus());
    }
}

package ru.sberdyshev.geekbrains.java.javaspringsecond.general.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {
    private String code;
    private String message;
    private Throwable cause;
    private StackTraceElement [] details;
}

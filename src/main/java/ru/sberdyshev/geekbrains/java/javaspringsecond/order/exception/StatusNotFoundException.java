package ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception;

//todo изменить toString во всех эксепшенах
public class StatusNotFoundException extends OrderBaseException {
    public StatusNotFoundException(OrderExceptionCode exceptionCode, String message) {
        super(exceptionCode, message);
    }

    @Override
    public String toString() {
        return "StatusNotFoundException{" +
                "code='" + getCode() + '\'' + ", " +
                "description='" + getDescription() + '\'' + ", " +
                "httpStatus='" + getHttpStatus() + '\'' + '}';
    }
}

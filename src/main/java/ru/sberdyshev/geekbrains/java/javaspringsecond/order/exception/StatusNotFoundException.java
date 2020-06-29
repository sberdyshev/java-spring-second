package ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception;

//todo изменить toString во всех эксепшенах
public class StatusNotFoundException extends OrderBaseException {
//    public StatusNotFoundException(OrderExceptionCode exceptionCode, String message) {
//        super(exceptionCode, message);
//    }
    public StatusNotFoundException(String message) {
        super(OrderExceptionCode.ERR_STATUS_NOT_FOUND, message);
    }

    @Override
    public String toString() {
        return "StatusNotFoundException{" +
                "code='" + getCode() + '\'' + ", " +
                "description='" + getDescription() + '\'' + ", " +
                "httpStatus='" + getHttpStatus() + '\'' + '}';
    }
}

package ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception;

public class OrderNotFountException extends OrderBaseException {
    public OrderNotFountException(String message) {
        super(OrderExceptionCode.ERR_ORDER_NOT_FOUND, message);
    }

    @Override
    public String toString() {
        return "OrderNotFountException{" +
                "code='" + getCode() + '\'' + ", " +
                "description='" + getDescription() + '\'' + ", " +
                "httpStatus='" + getHttpStatus() + '\'' + '}';
    }
}

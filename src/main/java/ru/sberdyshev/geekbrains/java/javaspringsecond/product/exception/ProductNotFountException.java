package ru.sberdyshev.geekbrains.java.javaspringsecond.product.exception;

public class ProductNotFountException extends ProductBaseException {
    public ProductNotFountException(String message) {
        super(ProductExceptionCode.ERR_PRODUCT_NOT_FOUND, message);
    }

    @Override
    public String toString() {
        return "ProductNotFountException{" +
                "code='" + getCode() + '\'' + ", " +
                "description='" + getDescription() + '\'' + ", " +
                "httpStatus='" + getHttpStatus() + '\'' + '}';
    }
}

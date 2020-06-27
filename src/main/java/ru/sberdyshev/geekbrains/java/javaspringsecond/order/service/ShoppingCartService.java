package ru.sberdyshev.geekbrains.java.javaspringsecond.order.service;

import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.OrderDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.ShoppingCartDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.dto.ProductDto;

import javax.servlet.http.HttpSession;
import java.util.UUID;

public interface ShoppingCartService {

    ShoppingCartDto getShoppingCart(HttpSession httpSession);

    ShoppingCartDto addProductToCart(HttpSession httpSession, UUID productId);

    ShoppingCartDto addProductToCart(HttpSession httpSession, ProductDto productDto);

    ShoppingCartDto deleteProductFromCart(HttpSession httpSession, UUID productId);

    ShoppingCartDto deleteProductFromCart(HttpSession httpSession, ProductDto productDto);

    ShoppingCartDto subtractProductFromCart(HttpSession session, UUID productId);

    ShoppingCartDto subtractProductFromCart(HttpSession session, ProductDto productDto);

    OrderDto confirmOrder(HttpSession httpSession);
}

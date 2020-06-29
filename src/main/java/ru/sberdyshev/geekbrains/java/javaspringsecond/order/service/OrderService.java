package ru.sberdyshev.geekbrains.java.javaspringsecond.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.ShoppingCart;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.OrderDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.ShoppingCartDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.dto.ProductDto;

import javax.servlet.http.HttpSession;
import java.util.UUID;

public interface OrderService {
    OrderDto getOrderForCurrentUser(UUID orderId);

    OrderDto getOrderForAdmin(UUID orderId);

    Page<OrderDto> getCurrentUserOrders(Pageable pageable);

    Page<OrderDto> getAllOrders(Pageable pageable);

    OrderDto saveCart(ShoppingCart shoppingCart);

    OrderDto addProductToOrder(UUID orderId, UUID productId);

    OrderDto deleteProductFromOrder(UUID orderId, UUID productId);

    OrderDto subtractProductFromOrder(UUID orderId, UUID productId);

    OrderDto updateOrderStatus(UUID orderId, String newStatusCode);

    OrderDto cancelOrder(UUID orderId);
}

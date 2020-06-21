package ru.sberdyshev.geekbrains.java.javaspringsecond.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.OrderDto;

import java.util.UUID;

public interface OrderService {
    OrderDto getOrder(UUID orderId);

//    Page<OrderDto> getOrders(UserDto userDto, Pageable pageable);

    Page<OrderDto> getOrders(Pageable pageable);

}

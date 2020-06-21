package ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.sberdyshev.geekbrains.java.javaspringsecond.general.config.formatter.MyCustomToStringJson;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.Order;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.OrderDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception.OrderNotFountException;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.repository.OrderRepository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.OrderService;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.dto.UserDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.service.UserService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public OrderDto getOrder(UUID orderId) {
        log.debug("getOrder() - Start with args: OrderId={}", orderId);
        //Get current userId
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userDto = userService.getUser(userName);
        log.debug("getOrder() - Requested by user: UserDto={}", userDto);
        //Get order by id and user id
        Optional<Order> optionalOrder = orderRepository.findByIdAndUserId(orderId, userDto.getId());
        if (!optionalOrder.isPresent()) {
            log.warn("getOrder() - Order with id={} is not found", orderId);
            throw new OrderNotFountException("Order with id=" + orderId + " is not found");
        }
        OrderDto orderDto =
                modelMapper.map(
                        optionalOrder.get(),
                        OrderDto.class
                );
        OrderDto orderDto1 = new OrderDto();
        System.out.println(orderDto1.toString());
        log.debug("getOrder() - Return value: OrderDto={}", orderDto);
        return orderDto;
    }

    //todo log
    //todo optional
    //todo implement
    //todo map dto - entity
//    @Override
//    public Page<OrderDto> getOrders(UserDto userDto, Pageable pageable) {
//        log.debug("getOrders() - Start with args: UserDto={}, Pageable={}", userDto, pageable);
//        Optional<UUID> optionalUserId = Optional.ofNullable(userDto.getId());
//        if (!optionalUserId.isPresent()) {
//            throw new
//        }
//        Page<Order> orderPage = orderRepository.findAllByUserId(userDto.getId(), pageable);
//        Type pageOrderDtoType = new TypeToken<Page<OrderDto>>() {
//        }.getType();
//        Page<OrderDto> orderDtoPage = modelMapper.map(orderPage, pageOrderDtoType);
//
//        List<Product> productList = productPage.getContent();
//        Type listProductDtoType = new TypeToken<List<ProductDto>>() {
//        }.getType();
//        List<ProductDto> productDtoList = modelMapper.map(productList, listProductDtoType);
//        Page<ProductDto> productDtoPage = new PageImpl<>(productDtoList);
//        log.debug("getAllProducts() - Return value: Page<ProductDto>={}", productDtoPage);
//
//
//        log.debug("getOrders() - Return value: OrderDto={}", );
//        return null;
//    }

    //todo log
    //todo optional
    //todo implement
    //todo map dto - entity
    @Override
    public Page<OrderDto> getOrders(Pageable pageable) {
        log.debug("getOrders() - Start with args: Pageable={}", pageable);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userDto = userService.getUser(userName);
        log.debug("getOrders() - Requested by user: UserDto={}", userDto);
        Page<Order> orderPage = orderRepository.findAllByUserId(userDto.getId(), pageable);
        List<Order> orderList = orderPage.getContent();
        Type listOrderDtoType = new TypeToken<List<OrderDto>>() {
        }.getType();
        List<OrderDto> orderDtoList = modelMapper.map(orderList, listOrderDtoType);
        Page<OrderDto> orderDtoPage = new PageImpl<>(orderDtoList);
        log.debug("getOrders() - Return value: Page<OrderDto>={} (List of values List<OrderDto>={})", orderDtoPage, orderDtoList);
        return orderDtoPage;
    }
}


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
import org.springframework.transaction.annotation.Transactional;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.Order;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.OrderStatus;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.ShoppingCart;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.Status;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.OrderDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.StatusDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception.OrderNotFountException;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.repository.OrderRepository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.OrderService;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.StatusService;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.domain.User;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.dto.UserDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.service.UserService;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    //todo сделать все переменные во всех сервисах и контроллерах финальными
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final StatusService statusService;

    @Override
//    @Transactional(readOnly = true)
    public OrderDto getOrder(UUID orderId) {
        //todo исправить логироование - разделить дебаг и инфо
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
//    @Transactional(readOnly = true)
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

    @Override
    @Transactional
    public OrderDto saveCart(ShoppingCart shoppingCart) {
        log.info("saveCart() - Start");
        log.debug("saveCart() - args: shoppingCart={}", shoppingCart);
        //create Order Object
        Order order = new Order();
        //set order user
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userDto = userService.getUser(userName);
        User user = modelMapper.map(userDto, User.class);
        order.setUser(user);
        //set order items
        order.setOrderItems(shoppingCart.getOrderItems());
        //set order status
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrder(order);
        orderStatus.setTimeChanged(new Date());
        StatusDto statusDto = statusService.getStatusByCode("NEW");
        Status statusData = modelMapper.map(statusDto, Status.class);
        orderStatus.setStatusData(statusData);
        order.setOrderStatus(orderStatus);
        //save Order
        log.debug("Before saving: Order={}", order);
        Order orderSaved = orderRepository.save(order);
        //map  to OrderDto
        OrderDto orderDto = modelMapper.map(orderSaved, OrderDto.class);
        log.debug("saveCart() - returning: OrderDto={}", orderDto);
        log.info("saveCart() - Finish");
        return orderDto;
    }
}


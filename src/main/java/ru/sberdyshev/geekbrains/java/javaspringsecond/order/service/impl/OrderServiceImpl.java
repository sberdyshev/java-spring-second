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
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.*;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.OrderDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.StatusDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception.OrderNotFountException;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception.StatusNotFoundException;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.repository.OrderRepository;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.OrderService;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.StatusService;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain.Product;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.dto.ProductDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.service.ProductService;
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
    private final ProductService productService;

    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrderForCurrentUser(UUID orderId) {
        //todo исправить логироование - разделить дебаг и инфо
        log.debug("getOrderForCurrentUser() - Start with args: OrderId={}", orderId);
        //Get current userId
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userDto = userService.getUser(userName);
        log.debug("getOrderForCurrentUser() - Requested by user: UserDto={}", userDto);
        //Get order by id and user id
        Optional<Order> optionalOrder = orderRepository.findByIdAndUserId(orderId, userDto.getId());
        if (!optionalOrder.isPresent()) {
            log.warn("getOrderForCurrentUser() - Order with id={} is not found", orderId);
            throw new OrderNotFountException("Order with id=" + orderId + " is not found");
        }
        OrderDto orderDto =
                modelMapper.map(
                        optionalOrder.get(),
                        OrderDto.class
                );
        OrderDto orderDto1 = new OrderDto();
        System.out.println(orderDto1.toString());
        log.debug("getOrderForCurrentUser() - Return value: OrderDto={}", orderDto);
        return orderDto;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrderForAdmin(UUID orderId) {
        //todo исправить логироование - разделить дебаг и инфо
        log.debug("getOrderForAdmin() - Start with args: OrderId={}", orderId);
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            log.warn("getOrderForAdmin() - Order with id={} is not found", orderId);
            throw new OrderNotFountException("Order with id=" + orderId + " is not found");
        }
        OrderDto orderDto =
                modelMapper.map(
                        optionalOrder.get(),
                        OrderDto.class
                );
        OrderDto orderDto1 = new OrderDto();
        System.out.println(orderDto1.toString());
        log.debug("getOrderForAdmin() - Return value: OrderDto={}", orderDto);
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
    @Transactional(readOnly = true)
    public Page<OrderDto> getCurrentUserOrders(Pageable pageable) {
        log.debug("getCurrentUserOrders() - Start with args: Pageable={}", pageable);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userDto = userService.getUser(userName);
        log.debug("getCurrentUserOrders() - Requested by user: UserDto={}", userDto);
        Page<Order> orderPage = orderRepository.findAllByUserId(userDto.getId(), pageable);
        List<Order> orderList = orderPage.getContent();
        Type listOrderDtoType = new TypeToken<List<OrderDto>>() {
        }.getType();
        List<OrderDto> orderDtoList = modelMapper.map(orderList, listOrderDtoType);
        Page<OrderDto> orderDtoPage = new PageImpl<>(orderDtoList, orderPage.getPageable(), orderPage.getTotalElements());
        log.debug("getCurrentUserOrders() - Return value: Page<OrderDto>={} (List of values List<OrderDto>={})", orderDtoPage, orderDtoList);
        return orderDtoPage;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderDto> getAllOrders(Pageable pageable) {
        log.debug("getAllOrders() - Start with args: Pageable={}", pageable);
        Page<Order> orderPage = orderRepository.findAll(pageable);
        List<Order> orderList = orderPage.getContent();
        Type listOrderDtoType = new TypeToken<List<OrderDto>>() {
        }.getType();
        List<OrderDto> orderDtoList = modelMapper.map(orderList, listOrderDtoType);
        Page<OrderDto> orderDtoPage = new PageImpl<>(orderDtoList, orderPage.getPageable(), orderPage.getTotalElements());
        log.debug("getAllOrders() - Return value: Page<OrderDto>={} (List of values List<OrderDto>={})", orderDtoPage, orderDtoList);
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

    @Override
    @Transactional
    public OrderDto addProductToOrder(UUID orderId, UUID productId) {
        log.info("addProductToOrder() - Start");
        log.debug("addProductToOrder() - args: orderId={}, productId={}", orderId, productId);
        Order currentOrder = orderRepository.getOne(orderId);
        List<OrderItem> currentOrderItemList = currentOrder.getOrderItems();
        boolean isProductFound = false;
        for (OrderItem orderItem : currentOrderItemList) {
            if (orderItem.getProduct().getId().equals(productId)) {
                log.debug("addProductToOrder() - found product with id={} among orderItems={}", productId, currentOrderItemList);
                isProductFound = true;
                orderItem.setCount(orderItem.getCount() + 1);
                break;
            }
        }
        if (!isProductFound) {
            log.debug("addProductToOrder() - found no product with id={} among orderItems={}", productId, currentOrderItemList);
            OrderItem newOrderItem = new OrderItem();
            ProductDto productDto = productService.getProduct(productId);
            Product product = modelMapper.map(productDto, Product.class);
            newOrderItem.setProduct(product);
            newOrderItem.setCount(1);
            currentOrder.addOrderItem(newOrderItem);
        }
        Order newOrder = orderRepository.save(currentOrder);
        OrderDto newOrderDto = modelMapper.map(newOrder, OrderDto.class);
        log.debug("addProductToOrder() - added product with id={} to shopping order, returning newOrderDto={}", productId, newOrderDto);
        log.info("addProductToOrder() - Finish");
        return newOrderDto;
    }

    @Override
    @Transactional
    public OrderDto deleteProductFromOrder(UUID orderId, UUID productId) {
        log.info("deleteProductFromOrder() - Start");
        log.debug("deleteProductFromOrder() - args: orderId={}, productId={}", orderId, productId);
        Order currentOrder = orderRepository.getOne(orderId);
        List<OrderItem> currentOrderItemList = currentOrder.getOrderItems();
        for (OrderItem orderItem : currentOrderItemList) {
            if (orderItem.getProduct().getId().equals(productId)) {
                log.debug("deleteProductFromOrder() - found product with id={} among orderItems={}", productId, currentOrderItemList);
                currentOrderItemList.remove(orderItem);
                break;
            }
        }
        if (currentOrderItemList.isEmpty()) {
            log.info("deleteProductFromOrder() - order is empty, canceling");
            StatusDto statusDtoCanceled = statusService.getStatusByCode("CANCELED");
            Status statusCanceled = modelMapper.map(statusDtoCanceled, Status.class);
            currentOrder.getOrderStatus().setStatusData(statusCanceled);
            currentOrder.getOrderStatus().setTimeChanged(new Date());
        }
        Order newOrder = orderRepository.save(currentOrder);
        OrderDto newOrderDto = modelMapper.map(newOrder, OrderDto.class);
        log.debug("deleteProductFromOrder() - deleted product with id={} from order, returning newOrderDto={}", productId, newOrderDto);
        log.info("deleteProductFromOrder() - Finish");
        return newOrderDto;
    }

    @Override
    @Transactional
    public OrderDto subtractProductFromOrder(UUID orderId, UUID productId) {
        log.info("subtractProductFromOrder() - Start");
        log.debug("subtractProductFromOrder() - args: orderId={}, productId={}", orderId, productId);
        Order currentOrder = orderRepository.getOne(orderId);
        List<OrderItem> currentOrderItemList = currentOrder.getOrderItems();
        for (OrderItem orderItem : currentOrderItemList) {
            if (orderItem.getProduct().getId().equals(productId)) {
                log.debug("subtractProductFromOrder() - found product with id={} among orderItems={}", productId, currentOrderItemList);
                if (orderItem.getCount() <= 1) {
                    log.info("subtractProductFromOrder() - the amount is equal or less than 1, deleting");
                    currentOrderItemList.remove(orderItem);
                } else {
                    orderItem.setCount(orderItem.getCount() - 1);
                }
                break;
            }
        }
        if (currentOrderItemList.isEmpty()) {
            log.info("subtractProductFromOrder() - order is empty, canceling");
            StatusDto statusDtoCanceled = statusService.getStatusByCode("CANCELED");
            Status statusCanceled = modelMapper.map(statusDtoCanceled, Status.class);
            currentOrder.getOrderStatus().setStatusData(statusCanceled);
            currentOrder.getOrderStatus().setTimeChanged(new Date());
        }
        Order newOrder = orderRepository.save(currentOrder);
        OrderDto newOrderDto = modelMapper.map(newOrder, OrderDto.class);
        log.debug("subtractProductFromOrder() - subtracted product with id={} from order, returning newOrderDto={}", productId, newOrderDto);
        log.info("subtractProductFromOrder() - Finish");
        return newOrderDto;
    }

    @Override
    @Transactional
    public OrderDto updateOrderStatus(UUID orderId, String newStatusCode) {
        log.info("updateOrderStatus() - Start");
        log.debug("updateOrderStatus() - args: orderId={}, newStatusCode={}", orderId, newStatusCode);
        StatusDto newStatusDto = statusService.getStatusByCode(newStatusCode);
        if (newStatusDto == null) {
            log.warn("updateOrderStatus() - Status type with code={} wasn't found", newStatusCode);
            throw new StatusNotFoundException("Status type with code=\"" + newStatusCode + "\" wasn't found");
        }
        Status newStatus = modelMapper.map(newStatusDto, Status.class);
        Order order = orderRepository.getOne(orderId);
        if (order == null) {
            log.warn("updateOrderStatus() - Order with id={} wasn't found", orderId);
            throw new OrderNotFountException("Order with id=\"" + orderId + "\" wasn't found");
        }
        order.getOrderStatus().setStatusData(newStatus);
        order.getOrderStatus().setTimeChanged(new Date());
        Order newOrder = orderRepository.save(order);
        OrderDto newOrderDto = modelMapper.map(newOrder, OrderDto.class);
        log.debug("updateOrderStatus() - updated status of order, returning OrderDto={}", newOrderDto);
        log.info("updateOrderStatus() - Finish");
        return newOrderDto;
    }

    @Override
    public OrderDto cancelOrder(UUID orderId) {
        log.info("cancelOrder() - Start");
        log.debug("cancelOrder() - args: orderId={}", orderId);
        OrderDto newOrderDto = updateOrderStatus(orderId, "CANCELED");
        log.debug("cancelOrder() - updated status of order, returning OrderDto={}", newOrderDto);
        log.info("cancelOrder() - Finish");
        return newOrderDto;
    }
}


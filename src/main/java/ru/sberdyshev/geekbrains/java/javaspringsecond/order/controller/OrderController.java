package ru.sberdyshev.geekbrains.java.javaspringsecond.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.OrderDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.OrderService;

import java.util.UUID;

@Slf4j
@Controller
public class OrderController {
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/orders")
    public String getAllOrdersPage(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                   Model model) {
        log.info("getAllOrdersPage() - Called GET /orders");
        log.debug("getAllOrdersPage() - args: pageNumber={}, pageSize={}", pageNumber, pageSize);
        model.addAttribute("page", pageNumber);
        model.addAttribute("pageSize", pageSize);
        //todo добавить на все страницы пагинацию
        Pageable page = PageRequest.of(0, 5);
        if (pageNumber != null && pageSize != null) {
            page = PageRequest.of(pageNumber - 1, pageSize);
        }
        Page<OrderDto> orderDtoPage = orderService.getOrders(page);
        model.addAttribute("orderDtoPage", orderDtoPage);
        log.info("getAllOrdersPage() - Finished GET /orders");
        log.debug("getAllOrdersPage() - Return value: orderDtoPage={}, model={}", orderDtoPage, model);
        return "orders";
    }

    @RequestMapping("/orders/{order-id}")
    public String getOneOrderById(@PathVariable(name = "order-id") UUID orderId,
                                  Model model) {
        log.info("getOneOrderById() - Called GET /orders/{order-id}");
        log.debug("getOneOrderById() - args: orderId={}", orderId);
        OrderDto orderDto = orderService.getOrder(orderId);
        model.addAttribute("orderDto", orderDto);
        model.addAttribute("test", "1");
        log.info("getOneOrderById() - Finished GET /orders/{order-id}");
        log.debug("getOneOrderById() - Return value: orderDto={}, model={}", orderDto, model);
        return "order-details";
    }
}

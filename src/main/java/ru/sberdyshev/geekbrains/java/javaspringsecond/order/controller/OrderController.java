package ru.sberdyshev.geekbrains.java.javaspringsecond.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.OrderDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.StatusDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.OrderService;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.StatusService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

//todo change request mapping на get\post mapping
@Slf4j
@Controller
public class OrderController {
    private OrderService orderService;
    private StatusService statusService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setStatusService(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping("/orders")
    public String getAllOrdersOfCurrentUserPage(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                Model model) {
        log.info("getAllOrdersOfCurrentUserPage() - Called GET /orders");
        log.debug("getAllOrdersOfCurrentUserPage() - args: pageNumber={}, pageSize={}", pageNumber, pageSize);
        model.addAttribute("page", pageNumber);
        model.addAttribute("pageSize", pageSize);
        //todo добавить на все страницы пагинацию
        Pageable page = PageRequest.of(0, 5);
        if (pageNumber != null && pageSize != null) {
            page = PageRequest.of(pageNumber - 1, pageSize);
        }
        Page<OrderDto> orderDtoPage = orderService.getCurrentUserOrders(page);
        model.addAttribute("orderDtoPage", orderDtoPage);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        log.info("getAllOrdersOfCurrentUserPage() - Finished GET /orders");
        log.debug("getAllOrdersOfCurrentUserPage() - Return value: orderDtoPage={}, model={}", orderDtoPage, model);
        return "orders";
    }

    @RequestMapping("/orders/{order-id}")
    public String getOneOrderById(@PathVariable(name = "order-id") UUID orderId,
                                  Model model) {
        log.info("getOneOrderById() - Called GET /orders/{order-id}");
        log.debug("getOneOrderById() - args: orderId={}", orderId);
        OrderDto orderDto = orderService.getOrderForCurrentUser(orderId);
        BigDecimal totalCost = orderDto.calculateTotalCost();
        model.addAttribute("orderDto", orderDto);
        model.addAttribute("totalCost", totalCost);
        log.info("getOneOrderById() - Finished GET /orders/{order-id}");
        log.debug("getOneOrderById() - Return value: orderDto={}, model={}", orderDto, model);
        return "order-details";
    }

    @GetMapping("/admin/orders")
    public String getAllOrdersForAdmin(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                       @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                       Model model) {
        log.info("getAllOrdersForAdmin() - Called GET /admin/orders");
        log.debug("getAllOrdersForAdmin() - args: pageNumber={}, pageSize={}", pageNumber, pageSize);
        model.addAttribute("page", pageNumber);
        model.addAttribute("pageSize", pageSize);
        //todo добавить на все страницы пагинацию
        Pageable page = PageRequest.of(0, 5);
        if (pageNumber != null && pageSize != null) {
            page = PageRequest.of(pageNumber - 1, pageSize);
        }
        Page<OrderDto> orderDtoPage = orderService.getAllOrders(page);
        model.addAttribute("orderDtoPage", orderDtoPage);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        log.info("getAllOrdersForAdmin() - Finished GET /admin/orders");
        log.debug("getAllOrdersForAdmin() - Return value: orderDtoPage={}, model={}", orderDtoPage, model);
        return "admin-orders";
    }

    @GetMapping("/admin/orders/{order-id}")
    public String getOrderByIdForAdmin(@PathVariable(name = "order-id") UUID orderId,
                                       Model model) {
        log.info("getOrderByIdForAdmin() - Called GET /admin/orders/{order-id}");
        log.debug("getOrderByIdForAdmin() - args: orderId={}", orderId);
        OrderDto orderDto = orderService.getOrderForAdmin(orderId);
        BigDecimal totalCost = orderDto.calculateTotalCost();
        List<StatusDto> statusDtoList = statusService.getAllStatuses();
        model.addAttribute("orderDto", orderDto);
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("statusDtoList", statusDtoList);
        log.info("getOrderByIdForAdmin() - Finished GET /admin/orders/{order-id}");
        log.debug("getOrderByIdForAdmin() - Return value: orderDto={}, model={}", orderDto, model);
        return "admin-order-details";
    }

    @PostMapping("/admin/orders/{order-id}/product/{product-id}/add")
    public String addProductToOrderForAdmin(@PathVariable(name = "order-id") UUID orderId,
                                            @PathVariable(name = "product-id") UUID productId,
                                            Model model) {
        log.info("addProductToOrderForAdmin() - Called POST /admin/orders/{order-id}/product/{product-id}/add");
        log.debug("addProductToOrderForAdmin() - args: orderId={}, productId={}", orderId, productId);
        OrderDto orderDto = orderService.addProductToOrder(orderId, productId);
        BigDecimal totalCost = orderDto.calculateTotalCost();
        model.addAttribute("orderDto", orderDto);
        model.addAttribute("totalCost", totalCost);
        log.info("addProductToOrderForAdmin() - Finished POST /admin/orders/{order-id}/product/{product-id}/add");
        log.debug("addProductToOrderForAdmin() - Return value: orderDto={}, totalCost={}, model={}", orderDto, totalCost, model);
        return "redirect:/admin/orders/" + orderId;
    }

    @PostMapping("/admin/orders/{order-id}/product/{product-id}/subtract")
    public String subtractProductFromOrderForAdmin(@PathVariable(name = "order-id") UUID orderId,
                                                   @PathVariable(name = "product-id") UUID productId,
                                                   Model model) {
        log.info("subtractProductFromOrderForAdmin() - Called POST /admin/orders/{order-id}/product/{product-id}/subtract");
        log.debug("subtractProductFromOrderForAdmin() - args: orderId={}, productId={}", orderId, productId);
        OrderDto orderDto = orderService.subtractProductFromOrder(orderId, productId);
        BigDecimal totalCost = orderDto.calculateTotalCost();
        model.addAttribute("orderDto", orderDto);
        model.addAttribute("totalCost", totalCost);
        log.info("subtractProductFromOrderForAdmin() - Finished POST /admin/orders/{order-id}/product/{product-id}/subtract");
        log.debug("subtractProductFromOrderForAdmin() - Return value: orderDto={}, totalCost={}, model={}", orderDto, totalCost, model);
        //todo проверить пустой order - другой редирект?
        return "redirect:/admin/orders/" + orderId;
    }

    @PostMapping("/admin/orders/{order-id}/product/{product-id}/delete")
    public String deleteProductFromOrderForAdmin(@PathVariable(name = "order-id") UUID orderId,
                                                 @PathVariable(name = "product-id") UUID productId,
                                                 Model model) {
        log.info("deleteProductFromOrderForAdmin() - Called POST /admin/orders/{order-id}/product/{product-id}/delete");
        log.debug("deleteProductFromOrderForAdmin() - args: orderId={}, productId={}", orderId, productId);
        OrderDto orderDto = orderService.deleteProductFromOrder(orderId, productId);
        BigDecimal totalCost = orderDto.calculateTotalCost();
        model.addAttribute("orderDto", orderDto);
        model.addAttribute("totalCost", totalCost);
        log.info("deleteProductFromOrderForAdmin() - Finished POST /admin/orders/{order-id}/product/{product-id}/delete");
        log.debug("deleteProductFromOrderForAdmin() - Return value: orderDto={}, totalCost={}, model={}", orderDto, totalCost, model);
        //todo проверить пустой order - другой редирект?
        return "redirect:/admin/orders/" + orderId;
    }

    @PostMapping("/admin/orders/{order-id}/status/new")
    public String setOrderStatusForAdmin(@PathVariable(name = "order-id") UUID orderId,
                                         @RequestParam(required = false) String selectedStatusCode,
                                         Model model) {
        log.info("setOrderStatusForAdmin() - Called POST /admin/orders/{order-id}/status/new");
        log.debug("setOrderStatusForAdmin() - args: orderId={}, selectedStatusCode={}", orderId, selectedStatusCode);
        OrderDto orderDto = orderService.updateOrderStatus(orderId, selectedStatusCode);
        model.addAttribute("orderDto", orderDto);
        log.info("setOrderStatusForAdmin() - Finished POST /admin/orders/{order-id}/status/new");
        log.debug("setOrderStatusForAdmin() - Return value: orderDto={}, model={}", orderDto, model);
        //todo проверить пустой order - другой редирект?
        return "redirect:/admin/orders/" + orderId;
    }

    @PostMapping("/admin/orders/{order-id}/cancel")
    public String cancelOrderForAdmin(@PathVariable(name = "order-id") UUID orderId,
                                      Model model) {
        log.info("cancelOrderForAdmin() - Called POST /admin/orders/{order-id}/cancel");
        log.debug("cancelOrderForAdmin() - args: orderId={}", orderId);
        OrderDto orderDto = orderService.cancelOrder(orderId);
        model.addAttribute("orderDto", orderDto);
        log.info("cancelOrderForAdmin() - Finished POST /admin/orders/{order-id}/cancel");
        log.debug("cancelOrderForAdmin() - Return value: orderDto={}, model={}", orderDto, model);
        //todo проверить пустой order - другой редирект?
        return "redirect:/admin/orders/" + orderId;
    }
}

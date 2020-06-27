package ru.sberdyshev.geekbrains.java.javaspringsecond.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.OrderDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.ShoppingCartDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.ShoppingCartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Slf4j
@Controller
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setOrderService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/cart")
    public String getShoppingCartPage(Model model,
                                      HttpSession httpSession) {
        log.info("getShoppingCartPage() - Called GET /orders/cart");
        //todo продумать пагинацию - должна ли она вообще быть в корзине?
        ShoppingCartDto shoppingCartDto = shoppingCartService.getShoppingCart(httpSession);
        model.addAttribute("shoppingCartDto", shoppingCartDto);
        log.info("getAllOrdersPage() - Finished GET /orders/cart");
        log.debug("getAllOrdersPage() - Return value: shoppingCartDto={}, model={}", shoppingCartDto, model);
        return "shopping-cart";
    }

    @PostMapping("/cart/product/add/{product-id}")
    public String addProductToShoppingCart(@PathVariable("product-id") UUID productId,
                                           HttpServletRequest httpServletRequest,
                                           Model model) {
        log.info("addProductToShoppingCart() - Called POST /orders/cart/product/add/{product-id}");
        log.debug("addProductToShoppingCart() - args: product-id={}", productId);
        shoppingCartService.addProductToCart(httpServletRequest.getSession(), productId);
        String referrer = httpServletRequest.getHeader("referer");
        log.info("addProductToShoppingCart() - Finished POST /orders/cart/product/add/{product-id}");
        log.debug("addProductToShoppingCart() - referrer={}", referrer);
        //todo проверить редирект - должен быть на shopping-cart же?
        return "redirect:" + referrer;
    }

    @PostMapping("/cart/product/delete/{product-id}")
    public String deleteProductFromShoppingCart(@PathVariable("product-id") UUID productId,
                                                HttpServletRequest httpServletRequest,
                                                Model model) {
        log.info("deleteProductFromShoppingCart() - Called POST /orders/cart/product/delete/{product-id}");
        log.debug("deleteProductFromShoppingCart() - args: product-id={}", productId);
        shoppingCartService.deleteProductFromCart(httpServletRequest.getSession(), productId);
        String referrer = httpServletRequest.getHeader("referer");
        log.info("deleteProductFromShoppingCart() - Finished POST /orders/cart/product/delete/{product-id}");
        log.debug("deleteProductFromShoppingCart() - referrer={}", referrer);
        //todo проверить редирект - должен быть на shopping-cart же?
        return "redirect:" + referrer;
    }

    @PostMapping("/cart/product/subtract/{product-id}")
    public String subtractProductFromShoppingCart(@PathVariable("product-id") UUID productId,
                                                HttpServletRequest httpServletRequest,
                                                Model model) {
        log.info("subtractProductFromShoppingCart() - Called POST /orders/cart/product/subtract/{product-id}");
        log.debug("subtractProductFromShoppingCart() - args: product-id={}", productId);
        shoppingCartService.subtractProductFromCart(httpServletRequest.getSession(), productId);
        String referrer = httpServletRequest.getHeader("referer");
        log.info("subtractProductFromShoppingCart() - Finished POST /orders/cart/product/subtract/{product-id}");
        log.debug("subtractProductFromShoppingCart() - referrer={}", referrer);
        //todo проверить редирект - должен быть на shopping-cart же?
        return "redirect:" + referrer;
    }

    @PostMapping("/orders/confirm")
    public ModelAndView confirmOrder(HttpServletRequest httpServletRequest,
                                     ModelMap model) {
        log.info("makeAnOrder() - Called POST /orders/cart/confirm with no args");
        OrderDto orderDto = shoppingCartService.confirmOrder(httpServletRequest.getSession());
        log.info("makeAnOrder() - Finished GET /orders/cart/confirm");
        log.debug("makeAnOrder() - New orderDto={}", orderDto);
        //todo редирект на страницу нового заказа - проверить
        model.addAttribute("orderDto", orderDto);
        return new ModelAndView("redirect:/orders/" + orderDto.getId(), model);
    }
}

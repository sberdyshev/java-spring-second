package ru.sberdyshev.geekbrains.java.javaspringsecond.general.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain.Product;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.service.ProductService;

@Slf4j
@Controller
public class MainController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String getIndexPage(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize,
                               Model model) {
        log.debug("Called GET / with args: pageNumber={}, pageSize={}", pageNumber, pageSize);
        model.addAttribute("page", pageNumber);
        model.addAttribute("pageSize", pageSize);
        //todo как по умолчанию инициализировать? Из настроек приложения? private static final переменными?
        Pageable page = PageRequest.of(0, 5);
        if (pageNumber != null && pageSize != null) {
            page = PageRequest.of(pageNumber - 1, pageSize);
        }
        //todo сделать маппер domain <-> dto
        Page<Product> productPage = productService.getAllProductsPageable(page);
        model.addAttribute("productPage", productPage);
        return "index";
    }
}

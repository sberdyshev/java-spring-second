package ru.sberdyshev.geekbrains.java.javaspringsecond.general.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.service.ProductService;

//todo change request mapping на get\post mapping
@Slf4j
@Controller
public class MainController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public ModelAndView getRootPage(ModelMap model) {
        log.info("getRootPage() - Called GET /");
        log.debug("getRootPage() - args: ModelMap={}", model);
        log.info("getRootPage() - Redirecting to /products");
        return new ModelAndView("redirect:/products", model);
    }

    @RequestMapping("/index")
    public ModelAndView getIndexPage(ModelMap model) {
        log.info("getIndexPage() - Called GET /index", model);
        log.debug("getIndexPage() - args: ModelMap={}", model);
        log.info("getIndexPage() - Redirecting to /products");
        return new ModelAndView("redirect:/products", model);
    }

    @RequestMapping("/index.html")
    public ModelAndView getIndexHtmlPage(ModelMap model) {
        log.info("getIndexHtmlPage() - Called GET /index.html", model);
        log.debug("getIndexHtmlPage() - args: ModelMap={}", model);
        log.info("getIndexHtmlPage() - Redirecting to /products");
        return new ModelAndView("redirect:/products", model);
    }
}

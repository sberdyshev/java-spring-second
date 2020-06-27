package ru.sberdyshev.geekbrains.java.javaspringsecond.product.controller;

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
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.dto.ProductDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.service.ProductService;
//todo change request mapping на get\post mapping
@Slf4j
@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")
    public String getAllProductsPage(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                     Model model) {
        log.debug("Called GET /products with args: pageNumber={}, pageSize={}", pageNumber, pageSize);
        model.addAttribute("page", pageNumber);
        model.addAttribute("pageSize", pageSize);
        Pageable page = PageRequest.of(0, 5);
        if (pageNumber != null && pageSize != null) {
            page = PageRequest.of(pageNumber - 1, pageSize);
        }
        Page<ProductDto> productDtoPage = productService.getAllProductsPageable(page);
        model.addAttribute("productDtoPage", productDtoPage);
        return "products";
    }

    @RequestMapping("/products/product/name/{product-name}")
    public String getOneProductByName(@PathVariable(name = "product-name") String productName,
                                      Model model) {
        log.debug("Called GET /products/product/name/{product-name} with args: productName={}", productName);
        ProductDto productDto = productService.getProduct(productName);
        model.addAttribute("productDto", productDto);
        return "product-details";
    }
}

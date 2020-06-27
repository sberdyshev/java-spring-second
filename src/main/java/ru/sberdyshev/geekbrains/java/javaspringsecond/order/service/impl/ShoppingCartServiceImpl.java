package ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.ShoppingCart;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.OrderDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.ShoppingCartDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.OrderService;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.ShoppingCartService;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain.Product;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.dto.ProductDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ModelMapper modelMapper;
    private final OrderService orderService;
    private final ProductService productService;
    private static final String CART_HTTP_ATTRIBUTE_NAME = "cart";

    @Override
    public ShoppingCartDto getShoppingCart(HttpSession httpSession) {
        log.info("getShoppingCart() - Start");
        log.debug("getShoppingCart() - args: httpSession={}", httpSession);
        ShoppingCart shoppingCart = getShoppingCartInternal(httpSession);
        ShoppingCartDto shoppingCartDto = modelMapper.map(
                shoppingCart,
                ShoppingCartDto.class
        );
        log.info("getShoppingCart() - Finish");
        log.debug("getShoppingCart() - Return: shoppingCartDto={}", shoppingCartDto);
        return shoppingCartDto;
    }

    @Override
    public ShoppingCartDto addProductToCart(HttpSession httpSession, UUID productId) {
        log.info("addProductToCart() - Start");
        log.debug("addProductToCart() - args: productId={}, httpSession={}", productId, httpSession);
        ProductDto productDto = productService.getProduct(productId);
        ShoppingCartDto shoppingCartDto = addProductToCart(httpSession, productDto);
        log.debug("addProductToCart() - added product with id={} to shopping cart, returning shoppingCartDto={}", productId, shoppingCartDto);
        log.info("addProductToCart() - Finish");
        return shoppingCartDto;
    }

    @Override
    public ShoppingCartDto addProductToCart(HttpSession httpSession, ProductDto productDto) {
        log.info("addProductToCart() - Start");
        log.debug("addProductToCart() - args: productDto={}, httpSession={}", productDto, httpSession);
        Product product = modelMapper.map(productDto, Product.class);
        ShoppingCart shoppingCart = getShoppingCartInternal(httpSession);
        shoppingCart.addProduct(product);
        ShoppingCartDto shoppingCartDto = modelMapper.map(shoppingCart, ShoppingCartDto.class);
        log.debug("addProductToCart() - added product={} to shopping cart, returning shoppingCartDto={}", product, shoppingCartDto);
        log.info("addProductToCart() - Finish");
        return shoppingCartDto;
    }

    @Override
    public ShoppingCartDto deleteProductFromCart(HttpSession httpSession, UUID productId) {
        log.info("deleteProductFromCart() - Start");
        log.debug("deleteProductFromCart() - args: productId={}, httpSession={}", productId, httpSession);
        ProductDto productDto = productService.getProduct(productId);
        ShoppingCartDto shoppingCartDto = deleteProductFromCart(httpSession, productDto);
        log.debug("deleteProductFromCart() - deleted product with id={} from shopping cart, returning shoppingCartDto={}", productId, shoppingCartDto);
        log.info("deleteProductFromCart() - Finish");
        return shoppingCartDto;
    }

    @Override
    public ShoppingCartDto deleteProductFromCart(HttpSession httpSession, ProductDto productDto) {
        log.info("deleteProductFromCart() - Start");
        log.debug("deleteProductFromCart() - args: productDto={}, httpSession={}", productDto, httpSession);
        Product product = modelMapper.map(productDto, Product.class);
        ShoppingCart shoppingCart = getShoppingCartInternal(httpSession);
        shoppingCart.removeProduct(product);
        ShoppingCartDto shoppingCartDto = modelMapper.map(shoppingCart, ShoppingCartDto.class);
        log.info("deleteProductFromCart() - deleted product={} from shopping cart, returning shoppingCartDto", productDto, shoppingCartDto);
        log.info("deleteProductFromCart() - Finish");
        return shoppingCartDto;
    }

    @Override
    public ShoppingCartDto subtractProductFromCart(HttpSession httpSession, UUID productId) {
        log.info("subtractProductFromCart() - Start");
        log.debug("subtractProductFromCart() - args: productId={}, httpSession={}", productId, httpSession);
        ProductDto productDto = productService.getProduct(productId);
        ShoppingCartDto shoppingCartDto = subtractProductFromCart(httpSession, productDto);
        log.debug("subtractProductFromCart() - subtracted product with id={} from shopping cart, returning shoppingCartDto={}", productId, shoppingCartDto);
        log.info("subtractProductFromCart() - Finish");
        return shoppingCartDto;
    }

    @Override
    public ShoppingCartDto subtractProductFromCart(HttpSession httpSession, ProductDto productDto) {
        log.info("subtractProductFromCart() - Start");
        log.debug("subtractProductFromCart() - args: productDto={}, httpSession={}", productDto, httpSession);
        Product product = modelMapper.map(productDto, Product.class);
        ShoppingCart shoppingCart = getShoppingCartInternal(httpSession);
        shoppingCart.subtractProduct(product);
        ShoppingCartDto shoppingCartDto = modelMapper.map(shoppingCart, ShoppingCartDto.class);
        log.info("subtractProductFromCart() - subtracted product={} from shopping cart, returning shoppingCartDto", productDto, shoppingCartDto);
        log.info("subtractProductFromCart() - Finish");
        return shoppingCartDto;
    }

    @Override
    public OrderDto confirmOrder(HttpSession httpSession) {
        log.info("confirmOrder() - Start");
        log.debug("confirmOrder() - args: httpSession={}", httpSession);
        ShoppingCart shoppingCart = getShoppingCartInternal(httpSession);
        OrderDto orderDto = orderService.saveCart(shoppingCart);
        clearShoppingCartInternal(httpSession);
        log.info("confirmOrder() - Start");
        log.debug("confirmOrder() - Returning orderDto={}", orderDto);
        return orderDto;
    }

    private ShoppingCart getShoppingCartInternal(HttpSession httpSession) {
        log.info("getCurrentShoppingCart() - Start");
        log.debug("getCurrentShoppingCart() - args: httpSession={}", httpSession);
        ShoppingCart shoppingCart = (ShoppingCart) httpSession.getAttribute(CART_HTTP_ATTRIBUTE_NAME);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            httpSession.setAttribute(CART_HTTP_ATTRIBUTE_NAME, shoppingCart);
        }
        log.debug("getCurrentShoppingCart() - Return shoppingCart={}", shoppingCart);
        log.info("getCurrentShoppingCart() - Finish");
        return shoppingCart;
    }

    private ShoppingCart clearShoppingCartInternal(HttpSession httpSession) {
        log.info("clearShoppingCartInternal() - Start");
        log.debug("clearShoppingCartInternal() - args: httpSession={}", httpSession);
        ShoppingCart newShoppingCart = new ShoppingCart();
        httpSession.setAttribute(CART_HTTP_ATTRIBUTE_NAME, newShoppingCart);
        log.debug("clearShoppingCartInternal() - Return new ShoppingCart={}", newShoppingCart);
        log.info("clearShoppingCartInternal() - Finish");
        return newShoppingCart;
    }
}

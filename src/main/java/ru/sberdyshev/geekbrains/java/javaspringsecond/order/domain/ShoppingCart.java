package ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//todo add logging
//todo add slf4j
@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class ShoppingCart {
    private List<OrderItem> orderItems;
    private BigDecimal totalCost;

    public ShoppingCart() {
        orderItems = new ArrayList<>();
        totalCost = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void addProduct(Product product) {
        log.info("addProduct() - Start");
        log.debug("addProduct() - args: Product={}", product);
        OrderItem orderItem = findOrderItemByProduct(product);
        if (orderItem == null) {
            orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setCount(0);
//            orderItem.setId(UUID.randomUUID());
            orderItems.add(orderItem);
        }
        orderItem.setCount(orderItem.getCount() + 1);
        recalculateTotalCost();
        log.info("addProduct() - Finish");
    }

    public void setCount(Product product, Integer productCount) {
        log.info("setCount() - Start");
        log.debug("setCount() - args: Product={}, productCount={}", product, productCount);
        OrderItem orderItem = findOrderItemByProduct(product);
        if (orderItem == null) {
            log.info("setCount() - Finish, didn't find orderItem with that product.");
            return;
        }
        orderItem.setCount(productCount);
        recalculateTotalCost();
        log.info("setCount() - Finish");
    }

    public void subtractProduct(Product product) {
        log.info("subtractProduct() - Start");
        log.debug("subtractProduct() - args: Product={}", product);
        OrderItem orderItem = findOrderItemByProduct(product);
        if (orderItem == null) {
            log.info("subtractProduct() - Finish, didn't find orderItem with that product.");
            return;
        }
        int count = orderItem.getCount();
        if (count <= 1) {
            orderItems.remove(orderItem);
        } else {
            orderItem.setCount(count - 1);
        }
        recalculateTotalCost();
        log.info("subtractProduct() - Finish");
    }

    public void removeProduct(Product product) {
        log.info("removeProduct() - Start");
        log.debug("removeProduct() - args: Product={}", product);
        OrderItem orderItem = findOrderItemByProduct(product);
        if (orderItem == null) {
            log.info("removeProduct() - Finish, didn't find orderItem with that product.");
            return;
        }
        orderItems.remove(orderItem);
        recalculateTotalCost();
        log.info("removeProduct() - Finish");
    }

    private void recalculateTotalCost() {
        log.info("recalculateTotalCost() - Start with no args");
        log.debug("recalculateTotalCost() - Current totalCost={}", totalCost);
        totalCost = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_HALF_UP);
        for (OrderItem orderItem : orderItems) {
            log.debug("recalculateTotalCost() - Processing OrderItem={}", orderItem);
            BigDecimal orderItemProductCount = new BigDecimal(orderItem.getCount());
            BigDecimal productPrice = orderItem.getProduct().getPrice();
            BigDecimal orderItemCost = productPrice.multiply(orderItemProductCount);
            log.debug("recalculateTotalCost() - orderItemProductCount={}, productPrice={}, orderItemCost={}",
                    orderItemProductCount,
                    productPrice,
                    orderItemCost);
            totalCost = totalCost.add(orderItemCost);
            log.debug("recalculateTotalCost() - totalCost + orderItemCost = {}", totalCost);
        }
        log.debug("recalculateTotalCost() - New totalCost={}", totalCost);
        log.info("recalculateTotalCost() - Finish");
    }

    private OrderItem findOrderItemByProduct(Product product) {
        return orderItems.stream().filter(o -> o.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String objectAsJson = mapper.writeValueAsString(this);
            return objectAsJson;
        } catch (JsonProcessingException jsonProcessingException) {
            return this.getClass().getName();
        }
    }
}

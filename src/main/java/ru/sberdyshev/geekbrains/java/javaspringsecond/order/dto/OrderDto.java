package ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.dto.UserDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
//todo add validation

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private UUID id;

    private UserDto user;

    private List<OrderItemDto> orderItems;

    private OrderStatusDto orderStatus;

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

    public BigDecimal calculateTotalCost() {
        BigDecimal totalCost = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_HALF_UP);
        for (OrderItemDto orderItemDto : orderItems) {
            BigDecimal orderItemDtoProductCount = new BigDecimal(orderItemDto.getCount());
            BigDecimal productPrice = orderItemDto.getProduct().getPrice();
            BigDecimal orderItemDtoCost = productPrice.multiply(orderItemDtoProductCount);
            totalCost = totalCost.add(orderItemDtoCost);
        }
        return totalCost;
    }
}

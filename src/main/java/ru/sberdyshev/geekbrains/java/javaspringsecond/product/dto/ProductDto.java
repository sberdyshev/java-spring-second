package ru.sberdyshev.geekbrains.java.javaspringsecond.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private String manufacturer;

    private String shortDescription;

    private String fullDescription;

    private BigDecimal price;

    @Override
    public String toString() {
        return "ProductDto{" +
                "id='" + id + '\'' + ", " +
                "name='" + name + '\'' + ", " +
                "manufacturer='" + manufacturer + '\'' + ", " +
                "shortDescription='" + shortDescription + '\'' + ", " +
                "fullDescription='" + fullDescription + '\'' + ", " +
                "price=" + price + '}';
    }
}

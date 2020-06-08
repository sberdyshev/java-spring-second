package ru.sberdyshev.geekbrains.java.javaspringsecond.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private UUID id;

    @NotNull
    @Length(max = 255, message = "The \"name\" field should be less than 255 characters")
    private String name;

    @NotNull
    @Length(max = 255, message = "The \"manufacturer\" field should be less than 255 characters")
    private String manufacturer;

    @NotNull
    @Length(max = 255, message = "The \"shortDescription\" field should be less than 255 characters")
    private String shortDescription;

    @NotNull
    @Size.List({
            @Size(min = 5, message = "The \"fullDescription\" field must be at least 5 characters"),
            @Size(max = 255, message = "The \"fullDescription\" field must be less than 255 characters")
    })
    private String fullDescription;

    @NotNull
    @Digits(integer = 15, fraction = 2, message = "The \"price\" field has incorrect format")
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

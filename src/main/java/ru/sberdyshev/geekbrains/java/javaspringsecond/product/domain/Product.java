package ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {


    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "full_description")
    private String fullDescription;

    @Column(name = "price")
    private BigDecimal price;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' + ", " +
                "name='" + name + '\'' + ", " +
                "manufacturer='" + manufacturer + '\'' + ", " +
                "shortDescription='" + shortDescription + '\'' + ", " +
                "fullDescription='" + fullDescription + '\'' + ", " +
                "price=" + price + '}';
    }
}

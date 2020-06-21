package ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sberdyshev.geekbrains.java.javaspringsecond.general.config.ObjectJsonPrinterConfig;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain.Product;

import javax.persistence.*;
import java.util.UUID;

//todo добавить toString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ord_order_item")
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "count")
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

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

package ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prd_product_image")
public class ProductImage {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "image_path")
    private String imagePath;

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

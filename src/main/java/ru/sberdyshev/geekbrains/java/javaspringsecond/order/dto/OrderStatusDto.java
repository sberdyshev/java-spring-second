package ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sberdyshev.geekbrains.java.javaspringsecond.general.config.ObjectJsonPrinterConfig;

import java.util.Date;
import java.util.UUID;

//todo add validation
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDto {

    private UUID id;

    private StatusDto statusData;

    private Date timeChanged;

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

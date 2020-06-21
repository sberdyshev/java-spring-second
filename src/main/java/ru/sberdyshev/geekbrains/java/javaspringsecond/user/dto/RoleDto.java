package ru.sberdyshev.geekbrains.java.javaspringsecond.user.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.sberdyshev.geekbrains.java.javaspringsecond.general.config.ObjectJsonPrinterConfig;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private UUID id;

    @NotNull
    @Length(max = 255, message = "The \"name\" field should be less than 255 characters")
    private String name;

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

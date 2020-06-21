package ru.sberdyshev.geekbrains.java.javaspringsecond.product.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.net.URL;
import java.util.UUID;

//todo добавить валидацию
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDto {

    private UUID id;

    @NotNull
    @Length(max = 255, message = "The \"imagePath\" field should be less than 255 characters")
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

    public boolean isNotAvailageImage() {
        log.debug("isNotAvailageImage() - Start with args: imagePath={}", imagePath);
        boolean result = true;
        if (imagePath == null || imagePath.isEmpty()) {
            result = true;
        } else {
            ClassLoader classLoader = getClass().getClassLoader();
            URL imageUrl = classLoader.getResource("static/img/products/" + imagePath);
            log.debug("isNotAvailageImage() - imageUrl={}", imageUrl);
            if (imageUrl != null) {
                result = false;
            }
//            if (imageUrl == null) {
//                result = false;
//            } else {
//                File imageFile = new File(imageUrl.getFile());
//                log.debug("isImageAvailable() - imageFile={}", imageFile);
//                log.debug("isImageAvailable() - imageFile.exists()={}", imageFile.exists());
//                log.debug("isImageAvailable() - imageFile.isFile()={}", imageFile.isFile());
//                if (imageFile != null && imageFile.exists() && imageFile.isFile()) {
//                    result = true;
//                } else {
//                    result = false;
//                }
//            }

        }

        log.debug("isNotAvailageImage() - Finish with result: {}", result);
        return result;
    }
}

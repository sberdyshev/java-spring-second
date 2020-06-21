package ru.sberdyshev.geekbrains.java.javaspringsecond.general.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectJsonPrinterConfig {

    @Override
    public String toString() {
//        log.info("toString() - Started");
//        log.info("toString() - Started");
//        log.debug("toString() - Canonical object name: {}", this.getClass().getCanonicalName());
//        log.debug("toString() - Simple object name: {}", this.getClass().getSimpleName());
//        log.debug("toString() - Object name: {}", this.getClass().getName());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String objectAsJson = mapper.writeValueAsString(this);
            return objectAsJson;
        } catch (JsonProcessingException jsonProcessingException) {
//            log.warn("There was an error in mapping class={} as JSON, exception={}",
//                    this.getClass().getCanonicalName(),
//                    jsonProcessingException);
            return this.getClass().getCanonicalName();
        }
//        log.info("toString() - Finished with default value");
//        log.debug("toString() - Return value={}", this.getClass().getCanonicalName());
//        return this.getClass().getCanonicalName();
    }
}

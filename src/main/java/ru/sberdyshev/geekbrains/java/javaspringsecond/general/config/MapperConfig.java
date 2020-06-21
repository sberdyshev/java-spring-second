package ru.sberdyshev.geekbrains.java.javaspringsecond.general.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.Order;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.Status;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.OrderDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.StatusDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.domain.Product;
import ru.sberdyshev.geekbrains.java.javaspringsecond.product.dto.ProductDto;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<Page<Order>, Page<OrderDto>> pageOrderToPageOrderDtoConverter = mapperContext ->
                Optional.ofNullable(mapperContext.getSource())
                        .map(source -> {
                            List<Order> orderList = source.getContent();
                            Type listOrderDtoType = new TypeToken<List<OrderDto>>() {
                            }.getType();
                            List<OrderDto> orderDtoList = modelMapper.map(orderList, listOrderDtoType);
                            Page<OrderDto> orderDtoPage = new PageImpl<>(orderDtoList);
                            return orderDtoPage;
                        })
                        .orElse(null);
        Converter<Page<Product>, Page<ProductDto>> pageProductToPageProductDtoConverter = mapperContext ->
                Optional.ofNullable(mapperContext.getSource())
                        .map(source -> {
                            List<Product> productList = source.getContent();
                            Type listProductDtoType = new TypeToken<List<ProductDto>>() {
                            }.getType();
                            List<ProductDto> productDtoList = modelMapper.map(productList, listProductDtoType);
                            Page<ProductDto> productDtoPage = new PageImpl<>(productDtoList);
                            return productDtoPage;
                        })
                        .orElse(null);
        Converter<Page<Status>, Page<StatusDto>> pageStatusToPageStatusDtoConverter = mapperContext ->
                Optional.ofNullable(mapperContext.getSource())
                        .map(source -> {
                            List<Status> statusList = source.getContent();
                            Type listStatusDtoType = new TypeToken<List<StatusDto>>() {
                            }.getType();
                            List<StatusDto> statusDtoList = modelMapper.map(statusList, listStatusDtoType);
                            Page<StatusDto> statusDtoPage = new PageImpl<>(statusDtoList);
                            return statusDtoPage;
                        })
                        .orElse(null);
        modelMapper.addConverter(pageOrderToPageOrderDtoConverter);
        modelMapper.addConverter(pageProductToPageProductDtoConverter);
        modelMapper.addConverter(pageStatusToPageStatusDtoConverter);
        return modelMapper;
    }
}

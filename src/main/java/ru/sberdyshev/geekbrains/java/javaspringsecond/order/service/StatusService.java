package ru.sberdyshev.geekbrains.java.javaspringsecond.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.StatusDto;

import java.util.List;

public interface StatusService {

    Page<StatusDto> getAllStatuses(Pageable pageable);

    List<StatusDto> getAllStatuses();

    StatusDto getStatusByCode(String statusCode);
}

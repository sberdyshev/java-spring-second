package ru.sberdyshev.geekbrains.java.javaspringsecond.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.Status;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.StatusDto;

public interface StatusService {

    Page<StatusDto> getAllStatuses(Pageable pageable);

    StatusDto getStatusByCode(String statusCode);
}

package ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.domain.Status;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.dto.StatusDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception.OrderExceptionCode;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.exception.StatusNotFoundException;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.repository.StatusReposiroty;
import ru.sberdyshev.geekbrains.java.javaspringsecond.order.service.StatusService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatusServiceImpl implements StatusService {

    private final StatusReposiroty statusRepository;
    private final ModelMapper modelMapper;

    //todo log
    //todo optional
    //todo implement
    //todo map dto - entity
    @Override
    @Transactional(readOnly = true)
    public Page<StatusDto> getAllStatuses(Pageable pageable) {
        log.debug("getAllStatuses() - Start with args: Pageable={}", pageable);
        Page<Status> statusPage = statusRepository.findAll(pageable);
        List<Status> statusList = statusPage.getContent();
        Type listStatusDtoType = new TypeToken<List<StatusDto>>() {
        }.getType();
        List<StatusDto> statusDtoList = modelMapper.map(statusList, listStatusDtoType);
        Page<StatusDto> statusDtoPage = new PageImpl<>(statusDtoList, statusPage.getPageable(), statusPage.getTotalElements());
        log.debug("getAllStatuses() - Finished");
        log.debug("getAllStatuses() - Return value: Page<StatusDto>={} (List of values List<OrderDto>={})", statusDtoPage, statusDtoList);
        return statusDtoPage;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatusDto> getAllStatuses() {
        log.debug("getAllStatuses() - Start with no args");
        List<Status> statusList = statusRepository.findAll();
        Type listStatusDtoType = new TypeToken<List<StatusDto>>() {
        }.getType();
        List<StatusDto> statusDtoList = modelMapper.map(statusList, listStatusDtoType);
        log.debug("getAllStatuses() - Finished");
        log.debug("getAllStatuses() - Return value: statusDtoList={}", statusDtoList);
        return statusDtoList;
    }

    @Override
//    @Transactional(readOnly = true)
    public StatusDto getStatusByCode(String statusCode) {
        log.info("getStatusByCode() - Start");
        log.debug("getStatusByCode() - Start with args: statusCode={}", statusCode);
        Optional<Status> optionalStatus = statusRepository.findByCode(statusCode);
        if (!optionalStatus.isPresent()) {
            log.warn("getStatusByCode() - Status with code={} is not found", statusCode);
            throw new StatusNotFoundException("Status with code \"" + statusCode + "\" is not found");
        }
        StatusDto statusDto = modelMapper.map(optionalStatus.get(), StatusDto.class);
        log.debug("getStatusByCode() - Return: StatusDto={}", statusDto);
        log.info("getStatusByCode() - Finish");
        return statusDto;
    }
}

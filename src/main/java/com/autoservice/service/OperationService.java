package com.autoservice.service;

import com.autoservice.dto.OperationDto;
import com.autoservice.entity.Operation;
import com.autoservice.entity.Order;
import com.autoservice.repository.OperationRepository;
import com.autoservice.service.mapper.OperationMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperationService {
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;
    private static final Logger log = LogManager.getLogger(OperationService.class);

    public OperationService(OperationRepository operationRepository, OperationMapper operationMapper) {
        this.operationRepository = operationRepository;
        this.operationMapper = operationMapper;
    }

    public Operation mapOperationDtoToOperation(OperationDto operationDto, Order order) {
        Operation operation = operationMapper.mapOperationDtoToOperation(operationDto);
        operation.setOrder(order);
        return operation;
    }

    public void save(Operation operation) {
        operationRepository.save(operation);
        log.info("Operation added");
    }
}

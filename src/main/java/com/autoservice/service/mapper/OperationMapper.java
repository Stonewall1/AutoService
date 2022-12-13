package com.autoservice.service.mapper;

import com.autoservice.dto.OperationDto;
import com.autoservice.entity.Operation;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component
public class OperationMapper {
    public Operation mapOperationDtoToOperation(OperationDto operationDto) {
        Operation operation = new Operation();
        operation.setOperationDescription(operationDto.getOperationDescription());
        operation.setPrice(operationDto.getPrice().setScale(2, RoundingMode.DOWN));
        return operation;
    }
}

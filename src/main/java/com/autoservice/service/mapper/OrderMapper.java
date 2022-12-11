package com.autoservice.service.mapper;

import com.autoservice.dto.OrderDto;
import com.autoservice.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order convertOrderDtoToOrder(OrderDto orderDto) {
        return new Order(orderDto.getCarOwner(),
                orderDto.getCar(),
                orderDto.getMaster(),
                orderDto.getRepairStart(),
                orderDto.getOrderCreation());
    }
}

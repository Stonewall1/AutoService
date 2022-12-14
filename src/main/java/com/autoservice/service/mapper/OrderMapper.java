package com.autoservice.service.mapper;

import com.autoservice.dto.OrderDto;
import com.autoservice.dto.PreparedOrderInfoDto;
import com.autoservice.entity.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {
    public Order convertOrderDtoToOrder(OrderDto orderDto, User user, Master master, Car car) {
        Order order = new Order();
        order.setCarOwner(user);
        order.setCar(car);
        order.setMaster(master);
        order.setRepairStart(orderDto.getRepairStart());
        order.setOrderCreation(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PENDING);
        return order;
    }

    public PreparedOrderInfoDto prepareOrderInfo(Order order) {
        return new PreparedOrderInfoDto(order.getRepairFinish(), order.getOrderStatus());
    }
}

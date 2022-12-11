package com.autoservice.service;

import com.autoservice.dto.OrderDto;
import com.autoservice.entity.Car;
import com.autoservice.entity.Master;
import com.autoservice.entity.Order;
import com.autoservice.entity.User;
import com.autoservice.repository.OrderRepository;
import com.autoservice.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order mapOrderDtoToOrder(OrderDto orderDto, User user, Master master, Car car) {
        return orderMapper.convertOrderDtoToOrder(orderDto, user, master, car);
    }
}

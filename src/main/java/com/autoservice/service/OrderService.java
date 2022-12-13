package com.autoservice.service;

import com.autoservice.dto.OrderDto;
import com.autoservice.entity.*;
import com.autoservice.exception.OrderNotFoundException;
import com.autoservice.repository.OrderRepository;
import com.autoservice.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Order update(Order order) {
        return orderRepository.save(order);
    }

    public Order mapOrderDtoToOrder(OrderDto orderDto, User user, Master master, Car car) {
        return orderMapper.convertOrderDtoToOrder(orderDto, user, master, car);
    }

    @Transactional(readOnly = true)
    public List<Order> findAllOrdersByUserId(long id) {
        return orderRepository.findAllByCarOwnerId(id);
    }

    @Transactional(readOnly = true)
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order findById(long id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else throw new OrderNotFoundException();
    }

    public Order addOperationToList(Order order, Operation operation) {
        order.getOperations().add(operation);
        return order;
    }
}

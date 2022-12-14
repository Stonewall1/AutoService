package com.autoservice.service;

import com.autoservice.dto.OrderDto;
import com.autoservice.dto.PreparedOrderInfoDto;
import com.autoservice.entity.*;
import com.autoservice.exception.OrderNotFoundException;
import com.autoservice.repository.OrderRepository;
import com.autoservice.service.mapper.OrderMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private static final Logger log = LogManager.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public Order save(Order order) {
        Order save = orderRepository.save(order);
        log.info("Record made , order created");
        return save;
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
        countOrderTotalPrice(order);
        return order;
    }

    private void countOrderTotalPrice(Order order) {
        BigDecimal bd = new BigDecimal("0");
        for (Operation operation : order.getOperations()) {
            bd = bd.add(operation.getPrice());
        }
        order.setTotalPrice(bd);
    }

    public PreparedOrderInfoDto prepareOrderInfo(Order order) {
        return orderMapper.prepareOrderInfo(order);
    }

    public Order editOrderInfo(Order order, PreparedOrderInfoDto preparedOrderInfoDto) {
        order.setRepairFinish(preparedOrderInfoDto.getRepairFinish());
        order.setOrderStatus(preparedOrderInfoDto.getOrderStatus());
        Order update = update(order);
        log.info("Order edited");
        return update;
    }
}

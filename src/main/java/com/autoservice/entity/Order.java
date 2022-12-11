package com.autoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @Lombok only while in development
 */
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User carOwner;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Master master;
    @ManyToMany
    private List<Operation> operations;
    private BigDecimal totalPrice;
    private LocalDateTime repairStart;
    private LocalDateTime repairFinish;
    private LocalDateTime orderCreation;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order(User carOwner, Car car, Master master, LocalDateTime repairStart, LocalDateTime orderCreation) {
        this.carOwner = carOwner;
        this.car = car;
        this.master = master;
        this.repairStart = repairStart;
        this.orderCreation = orderCreation;
    }
}

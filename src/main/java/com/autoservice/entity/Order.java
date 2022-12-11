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
    private Master master;
    @ManyToOne
    private User carOwner;
    @ManyToOne
    private Car car;
    @ManyToMany
    private List<Operation> operations;
    private BigDecimal totalPrice;
    private LocalDateTime orderCreation;
    private LocalDateTime repairStart;
    private LocalDateTime repairFinish;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}

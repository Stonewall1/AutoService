package com.autoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "operations")
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @Lombok only while in development
 */
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 1000)
    private String operationDescription;
    @ManyToOne
    private Order order;
    private BigDecimal price;

    @Override
    public String toString() {
        return "Operation{" +
                "operationDescription='" + operationDescription + '\'' +
                ", price=" + price +
                '}';
    }
}

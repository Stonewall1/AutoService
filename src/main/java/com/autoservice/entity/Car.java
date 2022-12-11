package com.autoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * @Lombok only while in development
 */
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private String model;
    private String colour;
    private String imageUrl;
    private double engineVolume;
    private String VIN;
    @ManyToOne
    private User owner;

    public Car(String brand, String model, String colour, String imageUrl, double engineVolume, String VIN) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.imageUrl = imageUrl;
        this.engineVolume = engineVolume;
        this.VIN = VIN;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", engineVolume=" + engineVolume +
                ", VIN='" + VIN + '\'' +
                '}';
    }
}

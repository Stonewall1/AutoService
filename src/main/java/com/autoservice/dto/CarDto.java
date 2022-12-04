package com.autoservice.dto;

import javax.validation.constraints.NotBlank;

public class CarDto {
    @NotBlank(message = "Field cant be empty")
    private String brand;
    @NotBlank(message = "Field cant be empty")
    private String model;
    @NotBlank(message = "Field cant be empty")
    private String colour;
    @NotBlank(message = "Field cant be empty")
    private String imageUrl;
    private double engineVolume;
    @NotBlank(message = "Field cant be empty")
    private String VIN;

    public CarDto() {
    }

    public CarDto(String brand, String model, String colour, String imageUrl, double engineVolume, String VIN) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.imageUrl = imageUrl;
        this.engineVolume = engineVolume;
        this.VIN = VIN;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }
}

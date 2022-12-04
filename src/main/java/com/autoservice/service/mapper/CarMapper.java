package com.autoservice.service.mapper;

import com.autoservice.dto.CarDto;
import com.autoservice.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public Car mapCarDtoToCar(CarDto carDto) {
        return new Car(carDto.getBrand(),
                carDto.getModel(),
                carDto.getColour(),
                carDto.getImageUrl(),
                carDto.getEngineVolume(),
                carDto.getVIN());
    }
}

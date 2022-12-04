package com.autoservice.service;

import com.autoservice.dto.CarDto;
import com.autoservice.entity.Car;
import com.autoservice.entity.User;
import com.autoservice.repository.CarRepository;
import com.autoservice.service.mapper.CarMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public Car save(CarDto carDto, User user) {
        Car car = mapToCar(carDto);
        car.setOwner(user);
        carRepository.save(car);
        return car;
    }

    private Car mapToCar(CarDto carDto) {
        return carMapper.mapCarDtoToCar(carDto);
    }
}

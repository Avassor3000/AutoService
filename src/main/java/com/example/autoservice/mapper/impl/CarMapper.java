package com.example.autoservice.mapper.impl;

import com.example.autoservice.dto.request.CarRequestDto;
import com.example.autoservice.mapper.RequestDtoMapper;
import com.example.autoservice.model.Car;
import com.example.autoservice.service.OwnerService;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements RequestDtoMapper<CarRequestDto, Car> {
    private final OwnerService ownerService;

    public CarMapper(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public Car mapToModel(CarRequestDto dto) {
        Car car = new Car();
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setManufacturingDate(dto.getManufacturingDate());
        car.setCarNumber(dto.getCarNumber());
        car.setOwner(ownerService.getById(dto.getOwnerId()));
        return car;
    }
}

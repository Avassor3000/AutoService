package com.example.autoservice.mapper.impl;

import com.example.autoservice.dto.request.CarStuffRequestDto;
import com.example.autoservice.mapper.RequestDtoMapper;
import com.example.autoservice.model.CarStuff;
import org.springframework.stereotype.Component;

@Component
public class CarStuffMapper implements RequestDtoMapper<CarStuffRequestDto, CarStuff> {
    @Override
    public CarStuff mapToModel(CarStuffRequestDto dto) {
        CarStuff carStuff = new CarStuff();
        carStuff.setName(dto.getName());
        carStuff.setStuffPrice(dto.getStuffPrice());
        return carStuff;
    }
}

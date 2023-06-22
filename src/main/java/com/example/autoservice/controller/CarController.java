package com.example.autoservice.controller;

import com.example.autoservice.dto.request.CarRequestDto;
import com.example.autoservice.mapper.impl.CarMapper;
import com.example.autoservice.model.Car;
import com.example.autoservice.service.CarService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @PostMapping
    public String create(@RequestBody CarRequestDto dto) {
        Car car = carService.save(carMapper.mapToModel(dto));
        return "Car is saved";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestBody CarRequestDto dto) {
        Car car = carMapper.mapToModel(dto);
        car.setId(id);
        carService.save(car);
        return "Car with id " + id + " is updated";
    }
}

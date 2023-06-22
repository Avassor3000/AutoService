package com.example.autoservice.controller;

import com.example.autoservice.dto.request.CarStuffRequestDto;
import com.example.autoservice.mapper.impl.CarStuffMapper;
import com.example.autoservice.model.CarStuff;
import com.example.autoservice.service.CarStuffService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stuff")
public class CarStuffController {
    private final CarStuffService carStuffService;
    private final CarStuffMapper carStuffMapper;

    public CarStuffController(CarStuffService carStuffService,
                              CarStuffMapper carStuffMapper) {
        this.carStuffService = carStuffService;
        this.carStuffMapper = carStuffMapper;
    }

    @PostMapping
    public String create(@RequestBody CarStuffRequestDto dto) {
        carStuffService.save(carStuffMapper.mapToModel(dto));
        return "Car stuff is saved";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestBody CarStuffRequestDto dto) {
        CarStuff carStuff = carStuffMapper.mapToModel(dto);
        carStuff.setId(id);
        carStuffService.save(carStuff);
        return "Car stuff with id " + id + " is updated";
    }
}

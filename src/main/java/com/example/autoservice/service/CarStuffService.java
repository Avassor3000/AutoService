package com.example.autoservice.service;

import com.example.autoservice.model.CarStuff;

public interface CarStuffService {
    CarStuff save(CarStuff carStuff);

    CarStuff getById(Long id);
}

package com.example.autoservice.service.impl;

import com.example.autoservice.dao.CarStuffDao;
import com.example.autoservice.model.CarStuff;
import com.example.autoservice.service.CarStuffService;
import org.springframework.stereotype.Service;

@Service
public class CarStuffServiceImpl implements CarStuffService {
    private final CarStuffDao carStuffDao;

    public CarStuffServiceImpl(CarStuffDao carStuffDao) {
        this.carStuffDao = carStuffDao;
    }

    @Override
    public CarStuff save(CarStuff carStuff) {
        return carStuffDao.save(carStuff);
    }

    @Override
    public CarStuff getById(Long id) {
        return carStuffDao.findById(id).orElse(null);
    }
}

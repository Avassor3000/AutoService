package com.example.autoservice.service.impl;

import com.example.autoservice.dao.CarDao;
import com.example.autoservice.model.Car;
import com.example.autoservice.service.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public Car save(Car car) {
        return carDao.save(car);
    }

    @Override
    public Car findById(Long id) {
        return carDao.findById(id).orElse(null);
    }
}

package com.example.autoservice.service;

import com.example.autoservice.model.CarStuff;
import com.example.autoservice.model.Order;

public interface OrderService {
    Order save(Order order);

    Order getById(Long orderId);

    double getFinalPrice(Long id);
}

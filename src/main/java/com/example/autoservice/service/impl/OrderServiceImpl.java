package com.example.autoservice.service.impl;

import com.example.autoservice.dao.OrderDao;
import com.example.autoservice.model.CarStuff;
import com.example.autoservice.model.Operation;
import com.example.autoservice.model.Order;
import com.example.autoservice.service.OrderService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order save(Order order) {
        return orderDao.save(order);
    }

    @Override
    public Order getById(Long orderId) {
        return orderDao.findById(orderId).orElse(null);
    }

    @Override
    public double getFinalPrice(Long id) {
        Order order = orderDao.findById(id).orElse(null);
        if (order != null) {
            double opDiscount = getOperationDiscount(order);
            double stuffDiscount = getStuffDiscount(order);
            double operationSum = order.getOperations()
                    .stream()
                    .mapToDouble(Operation::getPrice)
                    .sum();
            double stuffSum = order.getListOfStuff()
                    .stream()
                    .mapToDouble(CarStuff::getStuffPrice)
                    .sum();
            if (order.getOperations().size() == 1) {
                return operationSum;
            }
            return operationSum * opDiscount + stuffSum * stuffDiscount - 500;
        }
        return 0;
    }

    private double getOperationDiscount(Order order) {
        List<Order> orders = order.getCar().getOwner().getOrders();
        return 1 - (orders.size() * 0.01);
    }

    private double getStuffDiscount(Order order) {
        List<Order> orders = order.getCar().getOwner().getOrders();
        return 1 - (orders.size() * 0.02);
    }
}

package com.example.autoservice.mapper.impl;

import com.example.autoservice.dto.request.OwnerRequestDto;
import com.example.autoservice.dto.response.OwnerResponseDto;
import com.example.autoservice.mapper.RequestDtoMapper;
import com.example.autoservice.mapper.ResponseDtoMapper;
import com.example.autoservice.model.Car;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Owner;
import com.example.autoservice.service.CarService;
import com.example.autoservice.service.OrderService;
import com.example.autoservice.service.OwnerService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper implements RequestDtoMapper<OwnerRequestDto, Owner>,
        ResponseDtoMapper<OwnerResponseDto, Owner> {
    private final CarService carService;
    private final OrderService orderService;
    private final OwnerService ownerService;

    public OwnerMapper(CarService carService,
                       OrderService orderService,
                       OwnerService ownerService) {
        this.carService = carService;
        this.orderService = orderService;
        this.ownerService = ownerService;
    }

    @Override
    public Owner mapToModel(OwnerRequestDto dto) {
        Owner owner = new Owner();
        owner.setOwnerName(dto.getOwnerName());

        if (ownerService.getByOwnerName(dto.getOwnerName()) == null) {
            List<Car> cars = new ArrayList<>();
            Car car = carService.findById(dto.getCarId());
            cars.add(car);
            owner.setCars(cars);
            List<Order> orders = new ArrayList<>();
            Order order = orderService.getById(dto.getOrderId());
            orders.add(order);
            owner.setOrders(orders);
            return owner;
        }

        List<Car> cars = ownerService.getByOwnerName(dto.getOwnerName()).getCars();
        Car car = carService.findById(dto.getCarId());
        cars.add(car);
        owner.setCars(cars);
        List<Order> orders = ownerService.getByOwnerName(dto.getOwnerName()).getOrders();
        Order order = orderService.getById(dto.getOrderId());
        orders.add(order);
        owner.setOrders(orders);
        return owner;
    }

    @Override
    public OwnerResponseDto mapToDto(Owner owner) {
        OwnerResponseDto responseDto = new OwnerResponseDto();
        List<Long> listOrdersIds = owner.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList());
        responseDto.setOrderIds(listOrdersIds);
        return responseDto;
    }
}

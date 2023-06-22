package com.example.autoservice.mapper.impl;

import com.example.autoservice.dto.request.OrderRequestDto;
import com.example.autoservice.dto.response.OrderResponseDto;
import com.example.autoservice.mapper.RequestDtoMapper;
import com.example.autoservice.mapper.ResponseDtoMapper;
import com.example.autoservice.model.Order;
import com.example.autoservice.service.CarService;
import com.example.autoservice.service.CarStuffService;
import com.example.autoservice.service.OperationService;
import com.example.autoservice.service.OrderService;
import java.time.LocalDate;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements RequestDtoMapper<OrderRequestDto, Order>,
        ResponseDtoMapper<OrderResponseDto, Order> {
    private final CarService carService;
    private final OperationService operationService;
    private final CarStuffService carStuffService;
    private final OrderService orderService;

    public OrderMapper(CarService carService,
                       OperationService operationService,
                       CarStuffService carStuffService,
                       OrderService orderService) {
        this.carService = carService;
        this.operationService = operationService;
        this.carStuffService = carStuffService;
        this.orderService = orderService;
    }

    @Override
    public Order mapToModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setCar(carService.findById(dto.getCarId()));
        order.setDescription(dto.getDescription());
        order.setOrderTakeDate(LocalDate.now());
        order.setOperations(dto.getListOfOperationIds()
                .stream()
                .map(operationService::getById)
                .collect(Collectors.toList()));
        order.setListOfStuff(dto.getListOfStuffIds()
                .stream()
                .map(carStuffService::getById)
                .collect(Collectors.toList()));
        order.setOrderStatus(dto.getOrderStatus());
        return order;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setFinalPrice(order.getFinalPrice());
        return orderResponseDto;
    }
}

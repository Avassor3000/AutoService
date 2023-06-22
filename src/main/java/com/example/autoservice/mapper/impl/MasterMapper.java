package com.example.autoservice.mapper.impl;

import com.example.autoservice.dto.request.MasterRequestDto;
import com.example.autoservice.dto.response.MasterResponseDto;
import com.example.autoservice.mapper.RequestDtoMapper;
import com.example.autoservice.mapper.ResponseDtoMapper;
import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;
import com.example.autoservice.service.MasterService;
import com.example.autoservice.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MasterMapper implements RequestDtoMapper<MasterRequestDto, Master>,
        ResponseDtoMapper<MasterResponseDto, Master> {
    private final MasterService masterService;
    private final OrderService orderService;

    public MasterMapper(MasterService masterService, OrderService orderService) {
        this.masterService = masterService;
        this.orderService = orderService;
    }

    @Override
    public Master mapToModel(MasterRequestDto dto) {
        Master master = new Master();
        master.setName(dto.getName());
        if (masterService.getMasterByName(dto.getName()) == null && dto.getOrderId() == null) {
            List<Order> orders = new ArrayList<>();
            master.setCompletedOrders(orders);
            return master;
        }

        if (masterService.getMasterByName(dto.getName()) == null) {
            List<Long> orders = new ArrayList<>();
            orders.add(dto.getOrderId());
            master.setCompletedOrders(orders.stream()
                    .map(orderService::getById)
                    .collect(Collectors.toList()));
            return master;
        }

        List<Order> orders = masterService
                .getMasterByName(dto.getName())
                .getCompletedOrders();
        orders.add(orderService.getById(dto.getOrderId()));
        master.setCompletedOrders(orders);
        return master;
    }

    @Override
    public MasterResponseDto mapToDto(Master master) {
        MasterResponseDto masterResponseDto = new MasterResponseDto();
        masterResponseDto.setName(master.getName());
        masterResponseDto.setCompletedOrdersId(masterService
                .getMasterOrders(master.getId())
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        masterResponseDto.setSalary(masterService.getSalary(master.getId()));
        return masterResponseDto;
    }
}

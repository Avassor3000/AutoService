package com.example.autoservice.mapper.impl;

import com.example.autoservice.dto.request.OperationRequestDto;
import com.example.autoservice.mapper.RequestDtoMapper;
import com.example.autoservice.model.Operation;
import com.example.autoservice.service.MasterService;
import com.example.autoservice.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper implements RequestDtoMapper<OperationRequestDto, Operation> {
    private final OrderService orderService;
    private final MasterService masterService;

    public OperationMapper(OrderService orderService, MasterService masterService) {
        this.orderService = orderService;
        this.masterService = masterService;
    }

    @Override
    public Operation mapToModel(OperationRequestDto dto) {
        Operation operation = new Operation();
        operation.setOperationName(dto.getOperationName());
        operation.setOrder(orderService.getById(dto.getOrderId()));
        operation.setMaster(masterService.getMasterById(dto.getMasterId()));
        operation.setPayStatus(false);
        operation.setPrice(dto.getPrice());
        return operation;
    }
}

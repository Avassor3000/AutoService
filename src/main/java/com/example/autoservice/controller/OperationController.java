package com.example.autoservice.controller;

import com.example.autoservice.dto.request.OperationRequestDto;
import com.example.autoservice.mapper.impl.OperationMapper;
import com.example.autoservice.model.Operation;
import com.example.autoservice.service.OperationService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
public class OperationController {
    private final OperationService operationService;
    private final OperationMapper operationMapper;

    public OperationController(OperationService operationService,
                               OperationMapper operationMapper) {
        this.operationService = operationService;
        this.operationMapper = operationMapper;
    }

    @PostMapping
    public String create(@RequestBody OperationRequestDto dto) {
        operationService.save(operationMapper.mapToModel(dto));
        return "Operation is saved";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestBody OperationRequestDto dto) {
        Operation operation = operationMapper.mapToModel(dto);
        operation.setId(id);
        operationService.save(operation);
        return "Operation with id " + id + " is updated";
    }

    @PatchMapping("/{id}")
    public String updateStatus(@PathVariable Long id) {
        Operation operation = operationService.getById(id);
        operation.setId(id);
        operation.setPayStatus(true);
        operationService.save(operation);
        return "Operation`s status with operation id " + id + " is changed";
    }
}

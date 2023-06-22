package com.example.autoservice.service;

import com.example.autoservice.model.Operation;

public interface OperationService {
    Operation save(Operation operation);

    Operation getById(Long operationId);
}

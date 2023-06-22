package com.example.autoservice.service.impl;

import com.example.autoservice.dao.OperationDao;
import com.example.autoservice.model.Operation;
import com.example.autoservice.service.OperationService;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationDao operationDao;

    public OperationServiceImpl(OperationDao operationDao) {
        this.operationDao = operationDao;
    }

    @Override
    public Operation save(Operation operation) {
        return operationDao.save(operation);
    }

    @Override
    public Operation getById(Long operationId) {
        return operationDao.findById(operationId).orElse(null);
    }
}

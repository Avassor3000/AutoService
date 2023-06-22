package com.example.autoservice.service.impl;

import com.example.autoservice.dao.MasterDao;
import com.example.autoservice.model.Master;
import com.example.autoservice.model.Operation;
import com.example.autoservice.model.Order;
import com.example.autoservice.service.MasterService;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MasterServiceImpl implements MasterService {
    private final MasterDao masterDao;

    public MasterServiceImpl(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    @Override
    public Master save(Master master) {
        return masterDao.save(master);
    }

    @Override
    public Master getMasterByName(String masterName) {
        return masterDao.findByName(masterName).orElse(null);
    }

    @Override
    public Master getMasterById(Long masterId) {
        return masterDao.findById(masterId).orElse(null);
    }

    @Override
    public List<Order> getMasterOrders(Long masterId) {
        Master master = masterDao.findById(masterId).orElse(null);
        if (master != null) {
            return master.getCompletedOrders();
        }
        return Collections.emptyList();
    }

    @Override
    public double getSalary(Long masterId) {
        Master master = masterDao.findById(masterId).orElse(null);
        if (master != null) {
            double sum = master.getCompletedOrders()
                    .stream()
                    .map(Order::getOperations)
                    .flatMap(Collection::stream)
                    .map(Operation::getPrice)
                    .mapToDouble(Double::doubleValue)
                    .sum();
            return sum;
        }
        return 0;
    }
}

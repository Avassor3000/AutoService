package com.example.autoservice.service;

import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;
import java.math.BigDecimal;
import java.util.List;

public interface MasterService {
    Master save(Master master);

    Master getMasterByName(String masterSurname);

    Master getMasterById(Long masterId);

    List<Order> getMasterOrders(Long masterId);

    double getSalary(Long masterId);
}

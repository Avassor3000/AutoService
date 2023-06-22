package com.example.autoservice.controller;

import com.example.autoservice.dto.request.OrderRequestDto;
import com.example.autoservice.mapper.impl.OrderMapper;
import com.example.autoservice.model.CarStuff;
import com.example.autoservice.model.Operation;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.OrderStatus;
import com.example.autoservice.service.CarStuffService;
import com.example.autoservice.service.OrderService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final CarStuffService carStuffService;

    public OrderController(OrderService orderService,
                           OrderMapper orderMapper,
                           CarStuffService carStuffService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.carStuffService = carStuffService;
    }

    @PostMapping
    public String create(@RequestBody OrderRequestDto dto) {
        orderService.save(orderMapper.mapToModel(dto));
        return "Order is saved";
    }

    @PostMapping("/{id}")
    public String addStuff(@PathVariable Long id,
                           @RequestParam Long stuffId) {
        Order order = orderService.getById(id);
        List<CarStuff> listOfStuff = order.getListOfStuff();
        CarStuff carStuff = carStuffService.getById(stuffId);
        listOfStuff.add(carStuff);
        order.setListOfStuff(listOfStuff);
        orderService.save(order);
        return "Stuff is added to order";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestBody OrderRequestDto dto) {
        Order order = orderMapper.mapToModel(dto);
        order.setId(id);
        orderService.save(order);
        return "Order with id " + id + " is updated";
    }

    @PutMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam OrderStatus orderStatus) {
        Order order = orderService.getById(id);
        order.setOrderStatus(orderStatus);
        if (order.getOrderStatus().equals("done")
                || order.getOrderStatus().equals("not done")) {
            order.setOrderDoneDate(LocalDate.now());
        }
        orderService.save(order);
        return "Order status with id " + id + " is changed";
    }

    @GetMapping("{id}")
    public double getFinalPrice(@PathVariable Long id) {
        return orderService.getFinalPrice(id);
    }
}

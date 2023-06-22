package com.example.autoservice.controller;

import com.example.autoservice.dto.request.OwnerRequestDto;
import com.example.autoservice.dto.response.OwnerResponseDto;
import com.example.autoservice.mapper.impl.OwnerMapper;
import com.example.autoservice.model.Car;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Owner;
import com.example.autoservice.service.CarService;
import com.example.autoservice.service.OrderService;
import com.example.autoservice.service.OwnerService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;
    private final CarService carService;
    private final OrderService orderService;

    public OwnerController(OwnerService ownerService,
                           OwnerMapper ownerMapper,
                           CarService carService,
                           OrderService orderService) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
        this.carService = carService;
        this.orderService = orderService;
    }

    @PostMapping
    public String create(@RequestBody OwnerRequestDto dto) {
        Owner owner = ownerService.save(ownerMapper.mapToModel(dto));
        return "Owner is saved";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestBody OwnerRequestDto dto) {
        Owner owner = ownerService.getById(id);
        List<Car> cars = owner.getCars();
        cars.add(carService.findById(dto.getCarId()));
        owner.setCars(cars);
        List<Order> orders = owner.getOrders();
        orders.add(orderService.getById(dto.getOrderId()));
        owner.setOrders(orders);
        owner.setId(id);
        ownerService.save(owner);
        return "Owner with id " + id + " is updated";
    }

    @GetMapping("{id}")
    public OwnerResponseDto getOrders(@PathVariable Long id) {
        Owner owner = ownerService.getById(id);
        return ownerMapper.mapToDto(owner);
    }
}

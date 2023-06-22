package com.example.autoservice.controller;

import com.example.autoservice.dto.request.MasterRequestDto;
import com.example.autoservice.dto.response.OrderResponseDto;
import com.example.autoservice.mapper.impl.MasterMapper;
import com.example.autoservice.mapper.impl.OrderMapper;
import com.example.autoservice.model.Master;
import com.example.autoservice.service.MasterService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master")
public class MasterController {
    private final MasterService masterService;
    private final MasterMapper masterMapper;
    private final OrderMapper orderMapper;

    public MasterController(MasterService masterService,
                            MasterMapper masterMapper,
                            OrderMapper orderMapper) {
        this.masterService = masterService;
        this.masterMapper = masterMapper;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public String create(@RequestBody MasterRequestDto dto) {
        Master master = masterService.save(masterMapper.mapToModel(dto));
        return "Master is saved";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestBody MasterRequestDto dto) {
        Master master = masterMapper.mapToModel(dto);
        master.setId(id);
        masterService.save(master);
        return "Master with id " + id + " is updated";
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders(@RequestParam Long id) {
        return masterService.getMasterOrders(id)
                .stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/salary")
    public Double getMasterSalary(@RequestParam Long id) {
        return masterService.getSalary(id) * 0.4;
    }
}

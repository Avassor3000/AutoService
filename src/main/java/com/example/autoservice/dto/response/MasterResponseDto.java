package com.example.autoservice.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class MasterResponseDto {
    private String name;
    private List<Long> completedOrdersId;
    private double salary;
}

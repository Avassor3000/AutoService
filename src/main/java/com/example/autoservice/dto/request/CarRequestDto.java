package com.example.autoservice.dto.request;

import lombok.Data;

@Data
public class CarRequestDto {
    private String brand;
    private String model;
    private int manufacturingDate;
    private String carNumber;
    private Long ownerId;
}

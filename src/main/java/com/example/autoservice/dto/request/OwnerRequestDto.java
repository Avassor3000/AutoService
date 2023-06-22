package com.example.autoservice.dto.request;

import lombok.Data;

@Data
public class OwnerRequestDto {
    private String ownerName;
    private Long carId;
    private Long orderId;
}

package com.example.autoservice.dto.request;

import lombok.Data;

@Data
public class OperationRequestDto {
    private Long orderId;
    private String operationName;
    private Long masterId;
    private double price;
    private boolean payStatus;
}

package com.example.autoservice.dto.request;

import com.example.autoservice.model.OrderStatus;
import java.util.List;
import lombok.Data;

@Data
public class OrderRequestDto {
    private Long carId;
    private String description;
    private List<Long> ListOfOperationIds;
    private List<Long> listOfStuffIds;
    private OrderStatus orderStatus;
}

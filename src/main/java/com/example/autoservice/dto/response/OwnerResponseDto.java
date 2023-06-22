package com.example.autoservice.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class OwnerResponseDto {
    private List<Long> orderIds;
}

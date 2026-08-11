package com.carbontracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CarbonRequest {
    private Integer userId;

    private Double electricity;

    private Double travel;

    private Double waste;

}

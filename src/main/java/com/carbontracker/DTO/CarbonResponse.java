package com.carbontracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarbonResponse {

    private Integer id;

    private Double totalEmission;

    private String status;

    private String message;

}

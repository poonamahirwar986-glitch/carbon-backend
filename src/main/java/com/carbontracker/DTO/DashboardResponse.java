package com.carbontracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {
    private Double totalEmission;

    private Integer totalEntries;

    private Double averageEmission;

    private Integer treesRequired;

    private Double electricityEmission;

    private Double travelEmission;

    private Double wasteEmission;
}

package com.carbontracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private Double totalEmission;

    private Integer totalEntries;

    private Double averageEmission;

    private Integer treesRequired;

    private String reportType;

    private String generatedDate;

}

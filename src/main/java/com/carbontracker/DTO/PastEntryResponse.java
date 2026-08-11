package com.carbontracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PastEntryResponse {
    private String date;

    private Double electricity;

    private Double travel;

    private Double waste;

    private Double totalEmission;

    private String status;

}

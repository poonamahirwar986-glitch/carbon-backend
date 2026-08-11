package com.carbontracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AIResponse {

    private int carbonScore;
    private String status;
    private String highestSource;
    private String savingPotential;
    private String recommendation;
    private String reply;

}

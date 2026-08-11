package com.carbontracker.DTO;

import lombok.Data;

@Data
public class SettingsRequest {

    private Boolean notificationEnabled;
    private Boolean reportGenerationEnabled;
    private Boolean ecoTipsEnabled;
    private Double carbonGoal;
    private Integer treeGoal;

}

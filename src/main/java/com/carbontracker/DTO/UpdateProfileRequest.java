package com.carbontracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    private Integer id;

    private String fullName;

    private String phoneNumber;

    private String city;

    private String country;

    private String profileImage;
}

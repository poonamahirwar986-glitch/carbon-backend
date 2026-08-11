package com.carbontracker.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private Integer id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String city;

    private String country;

    private String profileImage;

    private String joinedDate;
}

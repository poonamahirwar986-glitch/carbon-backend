package com.carbontracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Integer id;

        private String fullName;

        private String email;

        private String message;
          private String token;

    }



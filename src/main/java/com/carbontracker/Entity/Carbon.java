package com.carbontracker.Entity;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "carbon_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carbon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // User Relationship
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


    @Column(nullable = false)
    private Double electricity;

    @Column(nullable = false)
    private Double travel;

    @Column(nullable = false)
    private Double waste;

    @Column(name = "total_emission", nullable = false)
    private Double totalEmission;

    @Column(nullable = false)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


}

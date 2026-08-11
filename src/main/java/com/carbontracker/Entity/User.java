package com.carbontracker.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String city;

    private String country;

    @Column(name = "profile_image",  columnDefinition="LONGTEXT")
    private String profileImage;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "notification_enabled")
    private Boolean notificationEnabled = true;
    private Boolean reportGenerationEnabled;
    private Boolean ecoTipsEnabled;

    @Column(name = "carbon_goal")
    private Double carbonGoal = 0.0;

    @Column(name = "tree_goal")
    private Integer treeGoal = 0;


    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Carbon> carbonRecords;
}

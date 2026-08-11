package com.carbontracker.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="ai_chat_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    private String message;


    @Column(columnDefinition = "TEXT")
    private String reply;


    private LocalDateTime createdAt;


    @PrePersist
    public void created(){
        createdAt = LocalDateTime.now();
    }
}

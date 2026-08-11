package com.carbontracker.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carbontracker.Entity.Carbon;
import com.carbontracker.Entity.User;

public interface CarbonRepository extends JpaRepository<Carbon,Long> {
    List<Carbon> findByUser(User user);
    List<Carbon> findByUserId(Long userId);
    Carbon findTopByUserIdOrderByCreatedAtDesc(Long userId);

}

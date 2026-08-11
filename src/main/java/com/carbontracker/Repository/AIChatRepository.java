package com.carbontracker.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.carbontracker.Entity.AIChat;

@Repository
public interface AIChatRepository  extends JpaRepository<AIChat,Long>{

    List<AIChat> findByUserIdOrderByCreatedAtAsc(Long userId);



    @Transactional
    @Modifying
    @Query("DELETE FROM AIChat a WHERE a.user.id = :userId")
    void deleteByUserId(@Param("userId") Integer userId);
}

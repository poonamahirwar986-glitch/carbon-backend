package com.carbontracker.Controller;

import com.carbontracker.DTO.AIRequest;
import com.carbontracker.DTO.AIResponse;
import com.carbontracker.Service.AIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.carbontracker.Repository.AIChatRepository;
import com.carbontracker.Entity.AIChat;

import java.util.List;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = {
        "http://127.0.0.1:3000",
        "http://localhost:3000"
})
public class AIController {

    @Autowired
    private AIService aiService;

    @Autowired
    private AIChatRepository aiChatRepository;

    @PostMapping("/chat")
    public AIResponse chat(@RequestBody AIRequest request) {
       return aiService.getAIAdvice(
               request.getUserId(),
               request.getMessage()
       );
    }
    @GetMapping("/history/{userId}")
    public List<AIChat> getChatHistory(@PathVariable Long userId) {

        return aiChatRepository.findByUserIdOrderByCreatedAtAsc(userId);

    }
}
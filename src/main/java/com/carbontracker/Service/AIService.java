package com.carbontracker.Service;

import com.carbontracker.DTO.AIResponse;
import com.carbontracker.Entity.Carbon;
import com.carbontracker.Repository.CarbonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbontracker.Config.GroqConfig;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.HashMap;

import java.util.List;
import com.carbontracker.Repository.AIChatRepository;
import com.carbontracker.Entity.User;
import com.carbontracker.Entity.AIChat;

@Service
public class AIService {

    @Autowired
    private CarbonRepository carbonRepository;
    @Autowired
    private AIChatRepository aiChatRepository;


    public AIResponse getAIAdvice(Long userId, String message) {
        // Fetch carbon data from database
        List<Carbon> records =
                carbonRepository.findByUserId(userId);
        if(records.isEmpty()) {
            return new AIResponse(
                    0,
                    "No Data",
                    "Unknown",
                    "0 kg CO₂/month",
                    "Please calculate your carbon footprint first.",
                    "Please calculate your carbon footprint first."
            );
        }
        double totalEmission = 0;
        double electricity = 0;
        double travel = 0;
        double waste = 0;

        // Calculate total values
        for(Carbon carbon : records) {
            totalEmission += carbon.getTotalEmission();

            electricity += carbon.getElectricity();

            travel += carbon.getTravel();

            waste += carbon.getWaste();
        }

        // Find highest emission source
        String highestSource;
        if(electricity >= travel && electricity >= waste){
            highestSource = "Electricity";
        }
        else if(travel >= electricity && travel >= waste){
            highestSource = "Travel";
        }
        else{
            highestSource = "Waste";
        }

        // Carbon Score Calculation
        int carbonScore;
        if(totalEmission <= 20){
            carbonScore = 95;
        }
        else if(totalEmission <= 50){
            carbonScore = 80;
        }
        else if(totalEmission <= 100){
            carbonScore = 60;
        }
        else{
            carbonScore = 40;
        }
        // Status
        String status;
        if(carbonScore >= 80){
            status = "Good";
        }
        else if(carbonScore >= 50){
            status = "Moderate";
        }
        else{
            status = "High";
        }
        // Saving Potential
        double saving;
        if(highestSource.equals("Electricity")){
            saving = electricity * 0.20;
        }
        else if(highestSource.equals("Travel")){
            saving = travel * 0.20;
        }
        else {
            saving = waste * 0.20;
        }

        // Recommendation
        String recommendation;

        if(highestSource.equals("Electricity")) {
            recommendation =
                    "Your electricity usage is the main contributor. "
                            + "Use LED lights, reduce unnecessary power usage "
                            + "and switch off unused appliances.";
        }
        else if(highestSource.equals("Travel")){
            recommendation =
                    "Your travel emissions are high. "
                            + "Try public transport, cycling or carpooling.";

        }
        else{
            recommendation =
                    "Your waste generation is high. "
                            + "Recycle materials and reduce single-use plastics.";
        }

        String prompt = """

You are a Carbon Sustainability AI Advisor.

User carbon details:

Carbon Score: %d/100
Status: %s
Highest Emission Source: %s
Saving Potential: %.2f kg CO2/month


User Question:
%s


Instructions:
- Answer only the user's question.
- Do not repeat welcome message.
- Do not show carbon score again unless user asks.
- Give practical sustainability advice.
- Keep answer friendly and simple.

""".formatted(
                carbonScore,
                status,
                highestSource,
                saving,
                message
        );


        String aiReply = getGroqResponse(prompt);

        AIChat chat = new AIChat();
        chat.setUser(records.get(0).getUser());
        chat.setMessage(message);
        chat.setReply(aiReply);
        aiChatRepository.save(chat);


        return new AIResponse(
                carbonScore,
                status,
                highestSource,
                String.format("%.2f kg CO₂/month", saving),
                recommendation,
                aiReply
        );

    }
    @Autowired
    private GroqConfig groqConfig;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.groq.com/openai/v1")
            .build();

    private String getGroqResponse(String prompt) {

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put(
                "model",
                "llama-3.3-70b-versatile"
        );

        requestBody.put(
                "messages",
                List.of(
                        Map.of(
                                "role",
                                "user",
                                "content",
                                prompt
                        )
                )
        );


        return webClient.post()

                .uri("/chat/completions")

                .header(
                        "Authorization",
                        "Bearer " + groqConfig.getApiKey()
                )

                .header(
                        "Content-Type",
                        "application/json"
                )

                .bodyValue(requestBody)

                .retrieve()

                .bodyToMono(Map.class)

                .map(response -> {

                    List choices =
                            (List) response.get("choices");

                    Map firstChoice =
                            (Map) choices.get(0);

                    Map message =
                            (Map) firstChoice.get("message");

                    return message
                            .get("content")
                            .toString();
                })

                .block();
    }



}
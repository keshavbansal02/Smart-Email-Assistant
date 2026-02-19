package com.keshav.email.assistant.Service;

import com.keshav.email.assistant.Entity.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder,
                                 @Value("${gemini.api.url}") String baseURL) {
        webClient = webClientBuilder.baseUrl(baseURL).build();
    }

    public String generateEmailReply(EmailRequest emailRequest) {
        // 1. Build the prompt
        String prompt = buildPrompt(emailRequest);

        // 2. Prepare the Request Body
        //        String body = String.format("""
        //                {
        //                    "contents": [
        //                      {
        //                        "parts": [
        //                          {
        //                            "text": "%s"
        //                          }
        //                        ]
        //                      }
        //                    ]
        //                  }""",prompt);

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        // 3. Send Request and Get Response

        String response = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/v1beta/models/gemini-3-flash-preview:generateContent").build())
                .header("x-goog-api-key",geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // 4. Extract response and
        return extractResponseContent(response);
    }
    private String extractResponseContent(String response) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode rootNode =  mapper.readTree(response);

            String finalResponse = rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            return finalResponse;
        } catch (Exception e) {
            return "Error processing request: " + e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("You are a smart email assistant. Generate ONLY ONE direct email reply. ");
        prompt.append("Do not provide multiple options. Do not include any introductory text or explanations. ");
        prompt.append("Provide only the email body as the final output. ");

        if (emailRequest.getEmailTone() != null && !emailRequest.getEmailTone().isEmpty()) {
            prompt.append("Use a ").append(emailRequest.getEmailTone()).append(" tone. ");
        }

        prompt.append("\nOriginal Mail : \n ").append(emailRequest.getEmailContent());

        return prompt.toString();
    }

}
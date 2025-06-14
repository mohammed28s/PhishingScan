package com.PhishingScan.email.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HuggingFaceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${huggingface.api.token}")
    private String apiToken;

    @Value("${huggingface.api.url}")
    private String modelUrl;

    public String classifyUrl(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> input = Map.of("inputs", url);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(input, headers);

        try {
            ResponseEntity<List> response = restTemplate.postForEntity(modelUrl, request, List.class);
            if (response.getBody() != null && !response.getBody().isEmpty()) {
                Map result = (Map) ((List) response.getBody()).get(0);
                return (String) result.get("label");
            }
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }

        return "unknown";
    }
}

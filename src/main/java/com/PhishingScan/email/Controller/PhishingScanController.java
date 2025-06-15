package com.PhishingScan.email.Controller;


import com.PhishingScan.email.service.HuggingFaceClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/phishing")
@RequiredArgsConstructor
public class PhishingScanController {

    private final HuggingFaceClient huggingFaceClient;

    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping("/check")
    public Map<String, Object> checkUrl(@Valid @RequestParam String url) {
        String result = huggingFaceClient.classifyUrl(url);
        return Map.of("url", url,
                "classification", result);
    }

}

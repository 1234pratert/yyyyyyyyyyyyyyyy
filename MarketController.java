package com.example.market;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/api")
public class MarketController {

    private final RestTemplate restTemplate = new RestTemplate();

    // 🌍 Fetch Global Market Data
    @GetMapping("/global")
    public Map<String, Double> getGlobalMarketData() {
        try {
            // Replace with real API URL
            String url = "https://api.example.com/global_markets";
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            return Map.of(
                "S&P 500", 0.46,
                "Nasdaq 100", -1.17,
                "Dow Jones", 0.09,
                "SGX Nifty", 0.05
            );
        }
    }

    // 🇮🇳 Fetch Indian Market Data
    @GetMapping("/indian")
    public Map<String, Double> getIndianMarketData() {
        try {
            // Replace with real API URL
            String url = "https://api.example.com/indian_markets";
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            return Map.of(
                "Nifty 50", 0.09,
                "Bank Nifty", 0.15,
                "India VIX", -0.02
            );
        }
    }

    // 📊 Calculate Sentiment Score
    @GetMapping("/sentiment")
    public Map<String, Double> getMarketSentiment() {
        Map<String, Double> globalData = getGlobalMarketData();
        Map<String, Double> indianData = getIndianMarketData();

        double globalScore = globalData.values().stream().mapToDouble(Double::doubleValue).average().orElse(0) * 100;
        double indianScore = indianData.values().stream().mapToDouble(Double::doubleValue).average().orElse(0) * 100;

        return Map.of("Global Sentiment", globalScore, "Indian Market Health", indianScore);
    }
}

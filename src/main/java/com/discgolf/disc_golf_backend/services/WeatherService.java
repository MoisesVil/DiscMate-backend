package com.discgolf.disc_golf_backend.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import org.json.JSONException;
import org.json.JSONObject;

@Service
public class WeatherService {

    private final WebClient webClient;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openweathermap.org").build();
    }

    public String getWindDirection(double lat, double lon, int userDir) {
        String apiKey = "e345b629e437a55ed3035673353e32c3";

        String response = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/data/3.0/onecall")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", "imperial")
                .queryParam("appid", apiKey)
                .build())
            .retrieve()
            .bodyToMono(String.class)
            .block();

        try {
            StringBuilder sb = new StringBuilder();

            JSONObject json = new JSONObject(response);
            JSONObject currentData = json.getJSONObject("current");
            if (currentData == null) return "No data available";
    
            double windSpeed = currentData.getDouble("wind_speed");
            int windDeg = currentData.getInt("wind_deg");

            int angleDiff = (windDeg - userDir + 360) % 360;

            if (angleDiff <= 45 || angleDiff >= 315) {
                sb.append("headwind,");
                sb.append(windSpeed);
                return sb.toString();
            } else if (angleDiff >= 135 && angleDiff <= 225) {
                sb.append("tailwind,");
                sb.append(windSpeed);
                return sb.toString();
            } else if (angleDiff > 45 && angleDiff < 135) {
                sb.append("right-to-left crosswind,");
                sb.append(windSpeed);
                return sb.toString();
            } else {
                sb.append("left-to-right crosswind,");
                sb.append(windSpeed);
                return sb.toString();
            }
        } catch (JSONException e) {
            System.err.println("Failed to parse wind JSON: " + e.getMessage());
            return "No data available";
        }
    }
}

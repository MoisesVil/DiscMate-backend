package com.discgolf.disc_golf_backend.services;

import com.discgolf.disc_golf_backend.dto.ShotRecommendationRequest;
import com.discgolf.disc_golf_backend.dto.ShotRecommendationResponse;
import org.springframework.stereotype.Service;

@Service
public class ShotRecommendationService {

    private final WeatherService weatherService;

    public ShotRecommendationService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public ShotRecommendationResponse recommendShot(ShotRecommendationRequest request) {
        ShotRecommendationResponse response = new ShotRecommendationResponse();

        String wind = weatherService.getWindDirection(request.getLat(), request.getLng(), request.getUserDirection());
        String[] parts = wind.split(",");
        String windDir = parts[0];
        //double windSpeed = Double.parseDouble(parts[1]);

        //System.out.println(wind);

        // All numbers are in feet
        String disc;
        if (request.getDistance() < 50) {
            disc = "Putter";
        } else if (request.getDistance() < 100) {
            disc = "Midrange";
        } else if (request.getDistance() < 300) {
            disc = "Fairway Driver";
        } else {
            disc = "Distance Driver";
        }

        String throwType = switch (windDir) {
            case "headwind" -> "AnHyzer";
            case "tailwind" -> "Hyzer";
            default -> "Flat";
        };

        String stability;

        if (windDir.equals("headwind")) {
            stability = "Overstable";
        } else if (windDir.equals("tailwind")) {
            stability = "Understable";
        } else {
            stability = "Stable";
        }

        response.setDisc(disc);
        response.setThrowType(throwType);
        response.setStability(stability);

        return response;
    }
}

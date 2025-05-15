package com.discgolf.disc_golf_backend.controllers;

import com.discgolf.disc_golf_backend.dto.ShotRecommendationRequest;
import com.discgolf.disc_golf_backend.dto.ShotRecommendationResponse;
import com.discgolf.disc_golf_backend.services.ShotRecommendationService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://172.20.10.3:3000")
@RestController
@RequestMapping("/api/shots")
public class ShotRecommendationController {

    private final ShotRecommendationService shotService;

    public ShotRecommendationController(ShotRecommendationService shotService) {
        this.shotService = shotService;
    }

    @PostMapping("/recommend")
    public ShotRecommendationResponse recommendShot(@RequestBody ShotRecommendationRequest request) {
        return shotService.recommendShot(request);
    }
}

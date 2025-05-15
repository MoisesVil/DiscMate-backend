package com.discgolf.disc_golf_backend.dto;

public class ShotRecommendationResponse {
    private String disc;
    private String throwType;
    private String stability;

    // Getters and setters
    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getThrowType() {
        return throwType;
    }

    public void setThrowType(String throwType) {
        this.throwType = throwType;
    }

    public String getStability() {
        return stability;
    }

    public void setStability(String stability) {
        this.stability = stability;
    }
}

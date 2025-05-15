package com.discgolf.disc_golf_backend.dto;

public class ShotRecommendationRequest {
    // always in feet
    private double distance;
    // User's lat coordinate
    private double lat;
    // User's lng coordinate
    private double lng;
    // ex: open, wooded, uphill
    private String lie;
    // direction user is facing in degrees (0 = N, 90 = E, 180 = S, 270 = W)
    private int userDirection;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getLie() {
        return lie;
    }

    public void setLie(String lie) {
        this.lie = lie;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getUserDirection() {
        return userDirection;
    }

    public void setUserDirection(int dir) {
        this.userDirection = dir;
    }
}

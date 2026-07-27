package com.pedroguths.nutrimetrics.dto;

public record PatientRequest(
        String name,
        double height,
        double weight,
        int age,
        String gender,
        String activityLevel) {
}

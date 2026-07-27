package com.pedroguths.nutrimetrics.dto;

import com.pedroguths.nutrimetrics.model.PatientModel;

public record PatientResponse(long id,
                              String name,
                              int age,
                              double height,
                              double weight,
                              String gender,
                              String activityLevel,
                              double bmi,
                              String bmiClassification,
                              double basalMetabolicRate,
                              double totalEnergy) {}
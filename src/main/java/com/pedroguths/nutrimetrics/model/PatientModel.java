package com.pedroguths.nutrimetrics.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "patient")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private double height;
    private double weight;
    private int age;
    private String gender;
    private String activityLevel;
    private double bmi;
    private String bmiClassification;
    private double basalMetabolicRate;
    private double totalEnergy;

}

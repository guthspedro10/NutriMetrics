package com.pedroguths.nutrimetrics.service;

import com.pedroguths.nutrimetrics.dto.PatientRequest;
import com.pedroguths.nutrimetrics.dto.PatientResponse;
import com.pedroguths.nutrimetrics.model.PatientModel;
import com.pedroguths.nutrimetrics.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientModel saveAndCalculate(PatientRequest body) {

        int age = body.age();
        double height = body.height();
        double weight = body.weight();
        String gender = body.gender();
        String activityLevel = body.activityLevel();

        double bmi = bmiCalculator(height, weight);
        String bmiClassification = bmiClassification(bmi);

        double basalMetabolicRate = tmbCalculator(gender, height, weight, age);
        double totalEnergy = energyCalculator(basalMetabolicRate, activityLevel);

        PatientModel patientModel = PatientModel.builder()
                .name(body.name())
                .age(age)
                .height(height)
                .weight(weight)
                .gender(gender)
                .activityLevel(activityLevel)
                .bmi(bmi)
                .bmiClassification(bmiClassification)
                .basalMetabolicRate(basalMetabolicRate)
                .totalEnergy(totalEnergy)
                .build();
        patientRepository.save(patientModel);

        return patientModel;
    }

    public List<PatientResponse> find(){

        List<PatientModel> patientResult = patientRepository.findAll();

        return patientResult.stream()
                .map(patient -> new PatientResponse(
                        patient.getId(),
                        patient.getName(),
                        patient.getAge(),
                        patient.getHeight(),
                        patient.getWeight(),
                        patient.getGender(),
                        patient.getActivityLevel(),
                        patient.getBmi(),
                        patient.getBmiClassification(),
                        patient.getBasalMetabolicRate(),
                        patient.getTotalEnergy())).toList();
    }

    private double bmiCalculator(double height, double weight) {
        return weight / (height * height);
    }

    private String bmiClassification(double bmi){

        String bmiClassification = null;

        if (bmi < 18.5) {
            bmiClassification = "Underweight";
        } else if (bmi <= 24.9) {
            bmiClassification = "Normal Weight";
        } else  if (bmi <= 29.9) {
            bmiClassification = "Overweight";
        } else if (bmi <= 34.9) {
            bmiClassification = "Obesity Class I";
        } else if (bmi <= 39.9) {
            bmiClassification = "Obesity Class II";
        } else if (bmi >= 40.0) {
            bmiClassification = "Morbid Obesity";
        }

        return bmiClassification;
    }

    private double tmbCalculator(String gender, double height, double weight, int age) {

        double basalMetabolicRate = 0.0;

        if (gender.equals("Male")){
            basalMetabolicRate = 88.362 + (13.397 * weight) + (4.799 * (height * 100)) - (5.677 * age);
        } else if (gender.equals("Female")){
            basalMetabolicRate = 447.593 + (9.247 * weight) + (3.098 * (height * 100)) - (4.330 * age);
        } else {
            throw new IllegalArgumentException("Invalid gender");
        }

        return basalMetabolicRate;
    }

    private double energyCalculator(double basalMetabolicRate, String activityLevel) {

        switch (activityLevel) {

            case "Sedentary":
                basalMetabolicRate = basalMetabolicRate * 1.2;
                break;
            case "Lightly Active":
                basalMetabolicRate = basalMetabolicRate * 1.375;
                break;
            case "Moderately Active":
                basalMetabolicRate = basalMetabolicRate * 1.55;
                break;
            case "Very Active":
                basalMetabolicRate = basalMetabolicRate * 1.725;
                break;
            case "Super Active":
                basalMetabolicRate = basalMetabolicRate * 1.9;
                break;
            default:
                throw new IllegalArgumentException("Invalid activity level");
        }
        return basalMetabolicRate;
    }
}

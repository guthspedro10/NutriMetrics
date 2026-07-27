package com.pedroguths.nutrimetrics.controller;

import com.pedroguths.nutrimetrics.dto.PatientRequest;
import com.pedroguths.nutrimetrics.dto.PatientResponse;
import com.pedroguths.nutrimetrics.model.PatientModel;
import com.pedroguths.nutrimetrics.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientModel> save (@RequestBody PatientRequest body){
        return ResponseEntity.ok(patientService.saveAndCalculate(body));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> findAll(){
        return ResponseEntity.ok(patientService.find());
    }

}

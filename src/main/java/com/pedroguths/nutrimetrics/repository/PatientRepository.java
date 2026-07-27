package com.pedroguths.nutrimetrics.repository;

import com.pedroguths.nutrimetrics.model.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {
}

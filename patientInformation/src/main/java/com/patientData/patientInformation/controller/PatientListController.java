package com.patientData.patientInformation.controller;

import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientListController {
    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping("/patientlist")
    public List<Patient> patientList() {
        return patientRepository.findAll();
    }

    @RequestMapping("/patient")
    public Optional<Patient> getPatient(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName, @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
            return patientRepository.findByLastNameAndFirstNameAndDateOfBirth(lastName, firstName, dateOfBirth);
    }
}

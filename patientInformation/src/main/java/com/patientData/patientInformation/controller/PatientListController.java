package com.patientData.patientInformation.controller;


import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.repository.PatientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(PatientListController.class);
    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping("/patientlist")
    public List<Patient> patientList() {
        logger.info("Get patient list");
        return patientRepository.findAll();
    }

    @RequestMapping("/patient")
    public Optional<Patient> getPatient(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName, @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
        logger.info("Get patient by last name = "+lastName+" first name = "+firstName+" and date of birth = "+ dateOfBirth );
        return patientRepository.findByLastNameAndFirstNameAndDateOfBirth(lastName, firstName, dateOfBirth);
    }
}

package com.patientData.patientInformation.repository;


import com.patientData.patientInformation.domain.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    static final Logger logger = LogManager.getLogger(PatientRepository.class);

    Optional<Patient> findByLastNameAndFirstNameAndDateOfBirth(String lastName, String firstName, Date dateOfBirth);

}

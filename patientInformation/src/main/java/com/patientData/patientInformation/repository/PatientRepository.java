package com.patientData.patientInformation.repository;

import com.patientData.patientInformation.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByLastName(String lastName);

    Patient findByPhoneNumber(String phoneNumber);
}

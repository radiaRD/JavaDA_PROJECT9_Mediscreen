package com.patientData.patientInformation.service;


import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.dto.PatientDto;
import org.springframework.ui.Model;

public interface IPatientService {
    String home(Model model, PatientDto patientDto);

    void showPatientByLastName(String lastName, Model model, PatientDto patientDto);

    void updatePatient(String lastName, PatientDto patientDto, Model model);

    String addPatient(PatientDto patientDto, Model model);

    void validate(PatientDto patientDto, Patient patient, Model model);

}

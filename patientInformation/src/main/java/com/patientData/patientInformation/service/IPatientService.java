package com.patientData.patientInformation.service;


import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.dto.PatientDto;
import org.springframework.ui.Model;

public interface IPatientService {
    String home(Model model, PatientDto patientDto);

    void showPatientById(Integer id, Model model, PatientDto patientDto);

    String updatePatient(Integer id, PatientDto patientDto, Model model);

    String addPatient(PatientDto patientDto, Model model);

    String validate(PatientDto patientDto, Model model);

    String deletePatient(Integer id, Model model, Patient patient);

}

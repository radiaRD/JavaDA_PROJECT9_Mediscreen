package com.patientData.patientInformation.service;


import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.dto.PatientDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;

public interface IPatientService {
    static final Logger logger = LogManager.getLogger(IPatientService.class);

    String home(Model model, PatientDto patientDto);

    void showPatientById(Integer id, Model model, PatientDto patientDto);

    String updatePatient(Integer id, PatientDto patientDto, Model model);

    String addPatient(PatientDto patientDto, Model model);

    String validate(PatientDto patientDto, Model model);

    String deletePatient(Integer id, Model model, Patient patient);

}

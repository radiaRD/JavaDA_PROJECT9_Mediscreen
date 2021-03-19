package com.patientAssessments.patientAssessments.service;

import com.patientAssessments.patientAssessments.dto.PatientAssessmentDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

public interface IPatientAssessmentService {

    String patientRiskLevel(Model model, String lastName, String firstName, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth);
    String getPatientList(Model model, PatientAssessmentDto patientAssessmentDto);
    String getPatientNotes(Model model, PatientAssessmentDto patientAssessmentDto);
    String getPatientNotesByLastName(Model model, PatientAssessmentDto patientAssessmentDto, String lastName, String firstName, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth);
    List<String> getTerms(String lastName, String firstName, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth);
    PatientAssessmentDto getRiskLevel(String lastName, String firstName, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth);
    String risk(String lastName, String firstName, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth);
    List<String> triggerterms();
}

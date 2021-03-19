package com.patientAssessments.patientAssessments.controller;

import com.patientAssessments.patientAssessments.dto.PatientAssessmentDto;
import com.patientAssessments.patientAssessments.proxies.PatientInformationProxy;
import com.patientAssessments.patientAssessments.service.IPatientAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class PatientController {
    @Autowired
    private IPatientAssessmentService patientAssessmentsService;

    @Autowired
    PatientInformationProxy patientInformationProxy;

    @RequestMapping("/assessment/patient/risk")
    public String getRiskLevelPatient(PatientAssessmentDto patientAssessmentDto, Model model, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName, @RequestParam(value = "dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
        if (patientInformationProxy.getPatient(lastName, firstName, dateOfBirth).isPresent()) {
            patientAssessmentsService.patientRiskLevel(model, lastName, firstName, dateOfBirth);
            return "patientAssessmentDto";
        }
        return "patientError";
    }

    @GetMapping("/patient/risk")
    public String getPatient(PatientAssessmentDto patientAssessmentDto) {
        return "patientAssessmentDtoRisk";
    }


    @GetMapping("/patient/list")
    public String getListPatient(Model model, PatientAssessmentDto patientAssessmentDto) {
        patientAssessmentsService.getPatientList(model, patientAssessmentDto);
        return "patientAssessmentDtoList";
    }

    @GetMapping("/patient/notes")
    public String getPatientNotes(Model model, PatientAssessmentDto patientAssessmentDto) {
        patientAssessmentsService.getPatientNotes(model, patientAssessmentDto);
        return "patientAssessmentDtoNotesList";
    }

    @GetMapping("/patient/notes/list")
    public String getPatientNotesByLastName(Model model, PatientAssessmentDto patientAssessmentDto, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName, @RequestParam(value = "dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
        patientAssessmentsService.getPatientNotesByLastName(model, patientAssessmentDto, lastName, firstName, dateOfBirth);
        return "patientAssessmentDtoNotesList";
    }
}
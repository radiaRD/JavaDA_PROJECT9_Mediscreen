package com.patientAssessments.patientAssessments.service;

import com.patientAssessments.patientAssessments.PatientAssessmentsApplication;
import com.patientAssessments.patientAssessments.beans.Notes;
import com.patientAssessments.patientAssessments.beans.Patient;
import com.patientAssessments.patientAssessments.dto.PatientAssessmentDto;
import com.patientAssessments.patientAssessments.proxies.PatientInformationProxy;
import com.patientAssessments.patientAssessments.proxies.PatientNotesProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import java.util.*;


@Service
public class PatientAssessmentsService implements IPatientAssessmentService {
    private static final Logger logger = LogManager.getLogger(PatientAssessmentsService.class);

    @Autowired
    PatientInformationProxy patientInformationProxy;

    @Autowired
    PatientNotesProxy patientNotesProxy;

    public PatientAssessmentsService(PatientInformationProxy patientInformationProxy, PatientNotesProxy patientNotesProxy) {
        this.patientInformationProxy = patientInformationProxy;
        this.patientNotesProxy = patientNotesProxy;
    }

    @Override
    public String patientRiskLevel(Model model, String lastName, String firstName, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
        if (patientInformationProxy.getPatient(lastName, firstName, dateOfBirth).isPresent()) {
            PatientAssessmentDto patientAssessmentDto = getRiskLevel(lastName, firstName, dateOfBirth);
            model.addAttribute("patientAssessmentDto", patientAssessmentDto);
            return "patientAssessmentDto";
        }
        return "patientError";
    }

    @Override
    public String getPatientList(Model model, PatientAssessmentDto patientAssessmentDto) {
        model.addAttribute("patientAssessmentDto", patientList());
        return "patientAssessmentDtoList";
    }

    @Override
    public String getPatientNotes(Model model, PatientAssessmentDto patientAssessmentDto) {
        model.addAttribute("patientAssessmentDto", notesList());
        return "patientAssessmentDtoNotesList";
    }

    @Override
    public String getPatientNotesByLastName(Model model, PatientAssessmentDto patientAssessmentDto, String lastName, String firstName, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
        model.addAttribute("patientAssessmentDto", listNotesPatient(lastName, firstName, dateOfBirth));
        return "patientAssessmentDtoNotesList";
    }


    public List<Patient> patientList() {
        return patientInformationProxy.patientList();
    }

    public List<Notes> notesList() {
        return patientNotesProxy.notesList();
    }

    public List<Notes> listNotesPatient(String lastName, String firstName, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
        return patientNotesProxy.listNotesPatient(lastName, firstName, dateOfBirth);
    }

    public Patient getPatient(String lastName, String firstName, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
        Patient patient = patientInformationProxy.getPatient(lastName, firstName, dateOfBirth).get();

        return patient;
    }

    @Override
    public List<String> getTerms(String lastName, String firstName, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
        List<String> terms = this.triggerterms();
        List<String> termsCondition = new ArrayList();
        List<Notes> notes = this.listNotesPatient(lastName, firstName, dateOfBirth);

        for (Notes note : notes) {
            for (String term : terms) {
                if (containsIgnoreCase(note.getNote(), term.substring(0, 4))) {
                    termsCondition.add(term);
                }
            }
        }
        Set<String> mySet = new HashSet<String>(termsCondition);
        List<String> termsConditionSet = new ArrayList<String>(mySet);
        return termsConditionSet;
    }

    @Override
    public PatientAssessmentDto getRiskLevel(String lastName, String firstName, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
        PatientAssessmentDto patientDto = new PatientAssessmentDto();
        patientDto.setLastName(lastName);
        patientDto.setFirstName(firstName);
        patientDto.setDateOfBirth(dateOfBirth);
        patientDto.setSex(getPatient(lastName, firstName, dateOfBirth).getSex());
        patientDto.setHomeAddress(getPatient(lastName, firstName, dateOfBirth).getHomeAddress());
        patientDto.setPhoneNumber(getPatient(lastName, firstName, dateOfBirth).getPhoneNumber());
        patientDto.setRiskLevel(risk(lastName, firstName, dateOfBirth));
        return patientDto;
    }

    @Override
    public String risk(String lastName, String firstName, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
        List<Notes> notes = this.listNotesPatient(lastName, firstName, dateOfBirth);
        PatientAssessmentDto patientDto = new PatientAssessmentDto();
        if (getTerms(lastName, firstName, dateOfBirth).size() == 0) {
            patientDto.setRiskLevel("None");
        } else if (getTerms(lastName, firstName, dateOfBirth).size() >= 2 && getTerms(lastName, firstName, dateOfBirth).size() < 6 && notes.get(1).getAge() > 30) {
            patientDto.setRiskLevel("Borderline");
        } else if (notes.get(1).getAge() < 30 && getPatient(lastName, firstName, dateOfBirth).getSex().equals("M") && getTerms(lastName, firstName, dateOfBirth).size() == 3) {
            patientDto.setRiskLevel("In Danger");
        } else if (notes.get(1).getAge() < 30 && getPatient(lastName, firstName, dateOfBirth).getSex().equals("F") && getTerms(lastName, firstName, dateOfBirth).size() == 4) {
            patientDto.setRiskLevel("In Danger");
        } else if (notes.get(1).getAge() > 30 && getTerms(lastName, firstName, dateOfBirth).size() >= 6 && getTerms(lastName, firstName, dateOfBirth).size() < 8) {
            patientDto.setRiskLevel("In Danger");
        } else if (notes.get(1).getAge() < 30 && getPatient(lastName, firstName, dateOfBirth).getSex().equals("M") && getTerms(lastName, firstName, dateOfBirth).size() == 5) {
            patientDto.setRiskLevel("Early onset");
        } else if (notes.get(1).getAge() < 30 && getPatient(lastName, firstName, dateOfBirth).getSex().equals("F") && getTerms(lastName, firstName, dateOfBirth).size() == 7) {
            patientDto.setRiskLevel("Early onset");
        } else if (notes.get(1).getAge() > 30 && getTerms(lastName, firstName, dateOfBirth).size() >= 8) {
            patientDto.setRiskLevel("Early onset");
        } else {
            patientDto.setRiskLevel("Undefined risk level");
        }
        return patientDto.getRiskLevel();
    }

    @Override
    public List<String> triggerterms() {
        List<String> terms = new ArrayList<>();
        terms.add("Hémoglobine A1C");
        terms.add("Microalbumine");
        terms.add("Taille");
        terms.add("Poids");
        terms.add("Fumeur");
        terms.add("Anormal");
        terms.add("Cholestérol");
        terms.add("Vertige");
        terms.add("Rechute");
        terms.add("Réaction");
        terms.add("Anticorps");
        return terms;
    }

    public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }

}

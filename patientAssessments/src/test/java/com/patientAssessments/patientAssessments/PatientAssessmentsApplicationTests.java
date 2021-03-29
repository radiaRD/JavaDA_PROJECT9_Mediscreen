package com.patientAssessments.patientAssessments;

import com.patientAssessments.patientAssessments.beans.Notes;
import com.patientAssessments.patientAssessments.beans.Patient;
import com.patientAssessments.patientAssessments.dto.PatientAssessmentDto;
import com.patientAssessments.patientAssessments.proxies.PatientInformationProxy;
import com.patientAssessments.patientAssessments.proxies.PatientNotesProxy;
import com.patientAssessments.patientAssessments.service.PatientAssessmentsService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class PatientAssessmentsApplicationTests {

    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    private PatientAssessmentsService patientAssessmentsService;
    @Mock
    private PatientNotesProxy patientNotesProxy;
    @Mock
    private PatientInformationProxy patientInformationProxy;
    List<Notes> notes = new ArrayList();


    @BeforeEach
    private void setUpPerTest() throws ParseException {
        Date dateOfBirth = simpleDateFormat.parse("1968-06-22");
        Notes note = new Notes("Ferguson", "Lucas", dateOfBirth, 52, "Hémoglobine A1C,Microalbumine,Taille,Poids,Cholestérol,Réaction");
        Notes note1 = new Notes("Ferguson", "Lucas", dateOfBirth, 52, "notes");
        notes.add(note);
        notes.add(note1);
        Patient patient = new Patient("Ferguson", "Lucas", dateOfBirth, "M", "2 Warren Street ", "387-866-1398");
        Mockito.when(patientNotesProxy.listNotesPatient("Ferguson", "Lucas", dateOfBirth)).thenReturn(notes);
        Mockito.when(patientInformationProxy.getPatient("Ferguson", "Lucas", dateOfBirth)).thenReturn(Optional.of(patient));
        patientAssessmentsService = new PatientAssessmentsService(patientInformationProxy, patientNotesProxy);
    }


    @Test
    public void getPatientTest() throws ParseException {
        Date dateOfBirth = simpleDateFormat.parse("1968-06-22");
        Patient patient = patientAssessmentsService.getPatient("Ferguson", "Lucas", dateOfBirth);
        Assert.assertEquals("Ferguson", patient.getLastName());
    }

    @Test
    public void getTermsTest() throws ParseException {
        Date dateOfBirth = simpleDateFormat.parse("1968-06-22");
        List<String> getTerms = patientAssessmentsService.getTerms("Ferguson", "Lucas", dateOfBirth);
        Assert.assertEquals(6, getTerms.size());
    }

    @Test
    public void riskTest() throws ParseException {
        Date dateOfBirth = simpleDateFormat.parse("1968-06-22");
        String risk = patientAssessmentsService.risk("Ferguson", "Lucas", dateOfBirth);
        Assert.assertEquals("In Danger", risk);
    }

    @Test
    public void getTriggerterms() {
        List<String> terms = patientAssessmentsService.triggerterms();
        Assert.assertEquals(11, terms.size());

    }

    @Test
    public void getRiskLevelTest() throws ParseException {
        Date dateOfBirth = simpleDateFormat.parse("1968-06-22");

        PatientAssessmentDto patientAssessmentDto = patientAssessmentsService.getRiskLevel("Ferguson", "Lucas", dateOfBirth);
        Assert.assertEquals("In Danger", patientAssessmentDto.getRiskLevel());

    }

    @Test
    void patientRiskLevelTest() throws ParseException {
        Date dateOfBirth = simpleDateFormat.parse("1968-06-22");
        PatientAssessmentDto patientAssessmentDto = new PatientAssessmentDto("Ferguson", "Lucas", dateOfBirth, "M", "2 Warren Street ", "387-866-1398", "In Danger");
        Assert.assertEquals("In Danger", patientAssessmentDto.getRiskLevel());

    }


}

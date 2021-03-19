package com.patientAssessments.patientAssessments;

import com.patientAssessments.patientAssessments.beans.Notes;
import com.patientAssessments.patientAssessments.beans.Patient;
import com.patientAssessments.patientAssessments.proxies.PatientInformationProxy;
import com.patientAssessments.patientAssessments.proxies.PatientNotesProxy;
import com.patientAssessments.patientAssessments.service.PatientAssessmentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientAssessmentTestIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PatientInformationProxy patientInformationProxy;
    @MockBean
    PatientNotesProxy patientNotesProxy;
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    PatientAssessmentsService patientAssessmentsService;

    @BeforeEach
    private void setUpPerTest() throws ParseException {
        Date dateOfBirth = simpleDateFormat.parse("1968-06-22");
        List<Notes> notes = new ArrayList();
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
    void getRiskLevelPatientTest() throws Exception {
        this.mockMvc.perform(get("/assessment/patient/risk?lastName=Ferguson&firstName=Lucas&dateOfBirth=1968-6-22")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("patientAssessmentDto"));
    }

    @Test
    void getPatientErrorTest() throws Exception {
        this.mockMvc.perform(get("/assessment/patient/risk?lastName=Ferguson&firstName=Luca&dateOfBirth=1968-6-20")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("patientError"));
    }

    @Test
    void getPatientTest() throws Exception {
        this.mockMvc.perform(get("/patient/risk")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("patientAssessmentDtoRisk"));

    }

    @Test
    void getListPatientTest() throws Exception {
        this.mockMvc.perform(get("/patient/list")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("patientAssessmentDtoList"));

    }

    @Test
    void getPatientNotesTest() throws Exception {
        this.mockMvc.perform(get("/patient/notes")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("patientAssessmentDtoNotesList"));

    }

    @Test
    void getPatientNotesByLastNameFirstNameAndDateOfBirthTest() throws Exception {
        this.mockMvc.perform(get("/patient/notes/list?lastName=Ferguson&firstName=Lucas&dateOfBirth=1968-6-22")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("patientAssessmentDtoNotesList"));

    }
}

package com.patientData.patientInformation;

import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.repository.PatientRepository;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PatientIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PatientRepository patientRepository;

    @BeforeEach
    public void cleanList() {
        patientRepository.deleteAll();
    }

    @Test
    void homeTest() throws Exception {
        this.mockMvc.perform(get("/patient/list")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("patientDtoList"))
                .andExpect(model().attribute("patientDto", Matchers.hasSize(0)));
    }

    @Test
    void showUpdateFormPatientTest() throws Exception {
        Patient patient = new Patient("Ferguson", "Lucas", new Date(1968 - 06 - 22), "M", "2 Warren Street ", "387-866-1399");
        patient = patientRepository.save(patient);
        this.mockMvc.perform(get("/patient/update/Ferguson")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("patientDto"))
                .andExpect(model().attribute("patientDto", Matchers.hasProperty("lastName", is("Ferguson"))));
    }

    @Test
    void updatePatientTest() throws Exception {
        Patient patient = new Patient("Ferguson", "Lucas", new Date(1968 - 06 - 22), "M", "2 Warren Street ", "387-866-1399");
        patient = patientRepository.save(patient);
        patient.setLastName("ferguson");
        this.mockMvc.perform(get("/patient/update/ferguson")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("patientDto"))
                .andExpect(model().attribute("patientDto", Matchers.hasProperty("lastName", is("ferguson"))));
    }

    @Test
    public void addPatientTest() throws Exception {
        this.mockMvc.perform(get("/patient/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("patientDtoAdd"));
    }

    @Test
    public void validateTest() throws Exception {
        Patient patient = new Patient("Ferguson", "Lucas", new Date(1968 - 06 - 22), "M", "2 Warren Street ", "387-866-1399");
        patient = patientRepository.save(patient);
        this.mockMvc.perform(get("/patient/list")).andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("patientDto", Matchers.hasSize(1)));
    }

    @Test
    void deletePatientTest() throws Exception {

        Patient patient = new Patient("Ferguson", "Lucas", new Date(1968 - 06 - 22), "M", "2 Warren Street ", "387-866-1399");
        patientRepository.save(patient);
        Integer id = patient.getId();
        this.mockMvc.perform(get("/patient/delete/"+id)).andDo(print())
                .andExpect(redirectedUrl("/patient/list"));
        this.mockMvc.perform(get("/patient/list")).andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("patientDto", Matchers.hasSize(0)));
    }

}

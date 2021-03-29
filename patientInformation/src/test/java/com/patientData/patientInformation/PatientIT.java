package com.patientData.patientInformation;

import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.repository.PatientRepository;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
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
        Integer id = patient.getId();
        this.mockMvc.perform(get("/patient/update/" + id)).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("patientDto"))
                .andExpect(model().attribute("patientDto", Matchers.hasProperty("lastName", is("Ferguson"))));
    }

    @Test
    void updatePatientTest() throws Exception {
        Patient patient = new Patient("Ferguson", "Lucas", new Date(1968 - 06 - 22), "M", "2 Warren Street ", "387-866-1399");
        patientRepository.save(patient);
        Integer id = patient.getId();
        this.mockMvc.perform(post("/patient/update/" + id)
                .param("lastName", "ferguson")
                .param("firstName", "Lucas")
                .param("dateOfBirth", "1968-06-22")
                .param("sex", "M")
                .param("homeAddress", "2 Warren Street")
                .param("phoneNumber", "387-866-1399")
                .contentType("text/html;charset=UTF-8"));
        this.mockMvc.perform(get("/patient/update/" + id)).andDo(print()).andExpect(status().isOk())
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
        this.mockMvc.perform(post("/patient/validate")
                .param("lastName", "Ferguson")
                .param("firstName", "Lucas")
                .param("dateOfBirth", "1968-06-22")
                .param("sex", "M")
                .param("homeAddress", "2 Warren Street")
                .param("phoneNumber", "387-866-1399")
                .contentType("text/html;charset=UTF-8"));
        this.mockMvc.perform(get("/patient/list")).andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("patientDto", Matchers.hasSize(1)));
        this.mockMvc.perform(post("/patient/validate")
                .param("lastName", "Ferguson")
                .param("firstName", "Lucas")
                .param("dateOfBirth", "1968-06-22")
                .param("sex", "M")
                .param("homeAddress", "2 Warren Street")
                .param("phoneNumber", "387-866-1399")
                .contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("patientExist"));
    }

    @Test
    void deletePatientTest() throws Exception {

        Patient patient = new Patient("Ferguson", "Lucas", new Date(1968 - 06 - 22), "M", "2 Warren Street ", "387-866-1399");
        patientRepository.save(patient);

        Integer id = patient.getId();
        this.mockMvc.perform(get("/patient/delete/" + id)).andDo(print());
        this.mockMvc.perform(get("/patient/list")).andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("patientDto", Matchers.hasSize(0)));
        this.mockMvc.perform(get("/patient/delete/1")).andDo(print())
                .andExpect(view().name("patientNotExist"));
    }

    @Test
    void getPatientListTest() throws Exception {

        this.mockMvc.perform(get("/patientlist")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void patientTest() throws Exception {

        this.mockMvc.perform(post("/patient/validate")
                .param("lastName", "Ferguson")
                .param("firstName", "Lucas")
                .param("dateOfBirth", "1968-06-22")
                .param("sex", "M")
                .param("homeAddress", "2 Warren Street")
                .param("phoneNumber", "387-866-1399")
                .contentType("text/html;charset=UTF-8"));
        this.mockMvc.perform(get("/patient?lastName=Ferguson&firstName=Lucas&dateOfBirth=1968-06-22")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", is("Ferguson")));
    }
}

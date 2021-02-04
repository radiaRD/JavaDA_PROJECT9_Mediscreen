package com.patientData.patientInformation;


import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.repository.PatientRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PatientTests {
    @Autowired
    private PatientRepository patientRepository;


    @Test
    public void patientTest() {
        Patient patient = new Patient("Ferguson", "Lucas", new Date(1968 - 06 - 22), "M", "2 Warren Street ", "387-866-1399");
        patient = patientRepository.save(patient);
        List<Patient> listResult = patientRepository.findAll();
        Assert.assertTrue(patient.getLastName().equals("Ferguson"));
        Assert.assertTrue(listResult.size() > 0);
    }
}
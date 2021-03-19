package com.patientData.patientInformation;


import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.repository.PatientRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
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


    @Test
    public void updatePatientTest() {
        Patient patient = new Patient("ferguson", "Lucas", new Date(1968 - 06 - 22), "M", "2 Warren Street ", "387-866-1399");
        patient = patientRepository.save(patient);
        patient.setLastName("Ferguson");
        patient = patientRepository.save(patient);
        Assert.assertTrue(patient.getLastName().equals("Ferguson"));
    }

    @Test
    public void addPatientTest() {
        Patient patient = new Patient("Ferguson", "Lucas", new Date(1968 - 06 - 22), "M", "2 Warren Street ", "387-866-1399");
        patient = patientRepository.save(patient);
        Assert.assertNotNull(patient.getId());
        Assert.assertTrue(patient.getLastName().equals("Ferguson"));
    }

    @Test
    public void deletePatientTest() {
        Patient patient = new Patient("Ferguson", "Lucas", new Date(1968 - 06 - 22), "M", "2 Warren Street ", "387-866-1398");
        patient = patientRepository.save(patient);
        Integer id = patient.getId();
        patientRepository.delete(patient);
        Optional<Patient> patientdelete = patientRepository.findById(id);
        Assert.assertFalse(patientdelete.isPresent());
    }
}

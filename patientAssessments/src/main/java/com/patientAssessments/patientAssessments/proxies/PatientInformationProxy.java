package com.patientAssessments.patientAssessments.proxies;

import com.patientAssessments.patientAssessments.beans.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@FeignClient(name = "patientInformation", url = "localhost:8081")
public interface PatientInformationProxy {

    @RequestMapping("/patientlist")
    List<Patient> patientList();

    @RequestMapping("/patient")
    Optional<Patient> getPatient(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName, @RequestParam(value = "dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth);
}

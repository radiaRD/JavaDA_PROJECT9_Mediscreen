package com.patientAssessments.patientAssessments.proxies;

import com.patientAssessments.patientAssessments.beans.Notes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@FeignClient(name = "patientNotes", url = "localhost:8083")
public interface PatientNotesProxy {

    @RequestMapping("/notesList")
    List<Notes> notesList();

    @RequestMapping("/noteslistPatient")
    List<Notes> listNotesPatient(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName, @RequestParam(value = "dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth);

}

package com.patientData.patientInformation.controller;

import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.dto.PatientDto;
import com.patientData.patientInformation.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @RequestMapping("/patient/list")
    public String home(Model model, PatientDto patientDto) {
        patientService.home(model, patientDto);
        return "patientDtoList";
    }

    @GetMapping("/patient/update/{lastName}")
    public String showPatientByLastName(@PathVariable("lastName") String lastName, Model model, PatientDto patientDto) {
        patientService.showPatientByLastName(lastName, model, patientDto);
        return "patientDto";
    }

}

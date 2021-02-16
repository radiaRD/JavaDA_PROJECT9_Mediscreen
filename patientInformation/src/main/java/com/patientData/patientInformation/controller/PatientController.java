package com.patientData.patientInformation.controller;

import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.dto.PatientDto;
import com.patientData.patientInformation.repository.PatientRepository;
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

    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping("/patient/list")
    public String home(Model model, PatientDto patientDto) {
        patientService.home(model, patientDto);
        return "patientDtoList";
    }

    @GetMapping("/patient/update/{id}")
    public String showPatientByLastName(@PathVariable("id") Integer id, Model model, PatientDto patientDto) {
        patientService.showPatientByLastName(id, model, patientDto);
        return "patientDto";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") Integer id, @Valid PatientDto patientDto,
                                BindingResult result, Model model) {

        if (!result.hasErrors()) {
            return patientService.updatePatient(id, patientDto, model);
        }
        return "patientDto";
    }

    @GetMapping("/patient/add")
    public String addPatient(PatientDto patientDto, Model model) {
        patientService.addPatient(patientDto, model);
        return "patientDtoAdd";
    }

    @PostMapping("/patient/validate")
    public String validate(@Valid PatientDto patientDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            return patientService.validate(patientDto, model);
        }
        return "patientDtoAdd";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable("id") Integer id, Model model, Patient patient) {
        return patientService.deletePatient(id, model, patient);
    }
}

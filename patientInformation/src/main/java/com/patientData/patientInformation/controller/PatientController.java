package com.patientData.patientInformation.controller;

import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.dto.PatientDto;
import com.patientData.patientInformation.exception.ResourceNotFoundException;
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

    @GetMapping("/patient/update/{lastName}")
    public String showPatientByLastName(@PathVariable("lastName") String lastName, Model model, PatientDto patientDto) {
        patientService.showPatientByLastName(lastName, model, patientDto);
        return "patientDto";
    }

    @PostMapping("/patient/update/{lastName}")
    public String updatePatient(@PathVariable("lastName") String lastName, @Valid PatientDto patientDto,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "patientDto";
        }
        patientService.updatePatient(lastName, patientDto, model);
        return "patientDtoList";
    }

    @GetMapping("/patient/add")
    public String addPatient(PatientDto patientDto, Model model) {
        patientService.addPatient(patientDto, model);
        return "patientDtoAdd";
    }

    @PostMapping("/patient/validate")
    public String validate(@Valid PatientDto patientDto, Patient patient, BindingResult result, Model model) {
        if (!result.hasErrors()) {

            if (patientRepository.findByPhoneNumber(patientDto.getPhoneNumber()) != null) {
                throw new ResourceNotFoundException("Patient already exists with this phone number");
            }
            patientService.validate(patientDto, patient, model);
            return "redirect:/patient/list";
        }
        return "patientDtoAdd";
    }
    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable("id") Integer id, Model model) {
        patientService.deletePatient(id, model);
        return "redirect:/patient/list";
    }
}

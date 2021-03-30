package com.patientData.patientInformation.controller;

import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.dto.PatientDto;
import com.patientData.patientInformation.repository.PatientRepository;
import com.patientData.patientInformation.service.IPatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(PatientController.class);

    @Autowired
    private IPatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping("/patient/list")
    public String home(Model model, PatientDto patientDto) {
        logger.info("Get patient List");
        patientService.home(model, patientDto);
        return "patientDtoList";
    }

    @GetMapping("/patient/update/{id}")
    public String showPatientById(@PathVariable("id") Integer id, Model model, PatientDto patientDto) {
        logger.info("Show patient by id = " + id);
        patientService.showPatientById(id, model, patientDto);
        return "patientDto";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") Integer id, @Valid PatientDto patientDto,
                                BindingResult result, Model model) {
        logger.info("Validate update patient by id = " + id);

        if (!result.hasErrors()) {
            return patientService.updatePatient(id, patientDto, model);
        }
        return "patientDto";
    }

    @GetMapping("/patient/add")
    public String addPatient(PatientDto patientDto, Model model) {
        logger.info("Add patient");
        patientService.addPatient(patientDto, model);
        return "patientDtoAdd";
    }

    @PostMapping("/patient/validate")
    public String validate(@Valid PatientDto patientDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            logger.info("Validate add patient");
            return patientService.validate(patientDto, model);
        }
        logger.info("Invalid add");
        return "patientDtoAdd";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable("id") Integer id, Model model, Patient patient) {
        logger.info("Delete patient by id = "+id);
        return patientService.deletePatient(id, model, patient);
    }

}

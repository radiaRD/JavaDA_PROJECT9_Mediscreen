package com.patientData.patientInformation.service;


import com.patientData.patientInformation.domain.Patient;
import com.patientData.patientInformation.dto.PatientDto;
import com.patientData.patientInformation.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import java.util.List;


@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String home(Model model, PatientDto patientDto) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patientDto", patients);
        return "patientDtoList";
    }

    @Override
    public void showPatientByLastName(String lastName, Model model, PatientDto patientDto) {
        Patient patient = patientRepository.findByLastName(lastName);
        patientDto = modelMapper.map(patient, PatientDto.class);
        model.addAttribute("patientDto", patientDto);
    }

}

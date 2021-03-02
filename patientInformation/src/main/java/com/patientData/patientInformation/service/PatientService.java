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
    public void showPatientByLastName(Integer id, Model model, PatientDto patientDto) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        patientDto = modelMapper.map(patient, PatientDto.class);
        model.addAttribute("patientDto", patientDto);
    }

    public String updatePatient(Integer id, PatientDto patientDto, Model model) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        patientRepository.save(patient);
        model.addAttribute("patientDto", patientRepository.findAll());
        return "patientDtoList";
    }

    public String addPatient(PatientDto patientDto, Model model) {
        model.addAttribute("patientDtoAdd", patientDto);
        return "patientDtoAdd";
    }

    @Override
    public String validate(PatientDto patientDto, Model model) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        if (patientRepository.findByLastNameAndFirstNameAndDateOfBirth(patientDto.getLastName(),patientDto.getFirstName(),patientDto.getDateOfBirth()).isPresent()) {
            return "patientExist";
        }
        patientRepository.save(patient);
        model.addAttribute("patientDto", patientRepository.findAll());
        return "patientDtoList";
    }

    public String deletePatient(Integer id, Model model, Patient patient) {
        if (patientRepository.findById(id).isPresent()) {
            patientRepository.delete(patient);
            model.addAttribute("patientDto", patientRepository.findAll());
            return "patientDtoList";
        }
        return "patientNotExist";
    }

}

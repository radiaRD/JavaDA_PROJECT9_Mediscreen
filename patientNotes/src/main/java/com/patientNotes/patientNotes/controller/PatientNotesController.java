package com.patientNotes.patientNotes.controller;

import com.patientNotes.patientNotes.model.Notes;
import com.patientNotes.patientNotes.repository.NotesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class PatientNotesController {
    private static final Logger logger = LogManager.getLogger(PatientNotesController.class);

    @Autowired
    NotesRepository notesRepository;

    @RequestMapping("/notesList")
    public List<Notes> notesList() {
        logger.info("Get notes List");
        return notesRepository.findAll();
    }

    @RequestMapping("/noteslistPatient")
    public List<Notes> listNotesPatient(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName, @RequestParam(value = "dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth, Model model) {
        logger.info("Get notes patient by last name = " + lastName+ ", first name = "+firstName+" and date of birth = "+ dateOfBirth);
        if (notesRepository.findByLastNameAndFirstNameAndDateOfBirth(lastName, firstName, dateOfBirth) != null) {
            return notesRepository.findByLastNameAndFirstNameAndDateOfBirth(lastName, firstName, dateOfBirth);
        }
        return null;
    }
}

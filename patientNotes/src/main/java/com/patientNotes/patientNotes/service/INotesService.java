package com.patientNotes.patientNotes.service;

import com.patientNotes.patientNotes.dto.NotesDto;
import com.patientNotes.patientNotes.model.Notes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;

import java.util.Date;


public interface INotesService {
    static final Logger logger = LogManager.getLogger(INotesService.class);

    String home(Model model, NotesDto notesDto);

    String getListNotes(Model model, String lastName, String firstName, Date dateOfBirth);

    void showNotesById(Long id, Model model, NotesDto notesDto);

    String updateNotes(Long id, NotesDto notesDto, Model model);

    void validate(NotesDto notesDto, Model model);

    String deleteNote(Long id, Model model, Notes notes);
}

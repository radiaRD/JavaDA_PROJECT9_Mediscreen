package com.patientNotes.patientNotes.service;

import com.patientNotes.patientNotes.dto.NotesDto;
import com.patientNotes.patientNotes.model.Notes;
import org.springframework.ui.Model;

public interface INotesService {
    String home(Model model, NotesDto notesDto);

}
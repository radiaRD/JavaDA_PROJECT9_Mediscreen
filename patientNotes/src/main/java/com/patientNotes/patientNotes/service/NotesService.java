package com.patientNotes.patientNotes.service;

import com.patientNotes.patientNotes.dto.NotesDto;
import com.patientNotes.patientNotes.model.Notes;
import com.patientNotes.patientNotes.repository.NotesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class NotesService implements INotesService {
    private LocalDate currentDate = LocalDate.now();
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    SequenceGeneratorService generateSequence;

    @Override
    public String home(Model model, NotesDto notesDto) {
        model.addAttribute("notesDto", notesRepository.findAll());
        return "notesDtoList";
    }

    @Override
    public String getListNotes(Model model, String lastName, String firstName, Date dateOfBirth) {
        model.addAttribute("notesDto", notesRepository.findByLastNameAndFirstNameAndDateOfBirth(lastName, firstName, dateOfBirth));
        return "notesDtoList";
    }

    @Override
    public void showNotesById(Long id, Model model, NotesDto notesDto) {
        Notes notes = notesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + id));
        notesDto = modelMapper.map(notes, NotesDto.class);
        model.addAttribute("notesDto", notesDto);
    }

    @Override
    public String updateNotes(Long id, NotesDto notesDto, Model model) {
        LocalDate dateOfBirth = this.convertToLocalDate(notesDto.getDateOfBirth());
        int age = this.calculateAge(dateOfBirth, currentDate);
        notesDto.setAge(age);
        Notes notes = modelMapper.map(notesDto, Notes.class);
        List<Notes> notesList = updatepatient(id, notes);
        for (Notes note : notesList) {
            note.setLastName(notes.getLastName());
            note.setFirstName(notes.getFirstName());
            note.setDateOfBirth(notes.getDateOfBirth());
            note.setAge(notes.getAge());
            notesRepository.save(note);
        }
        notesRepository.save(notes);
        model.addAttribute("notesDto", notesRepository.findAll());
        return "notesDtoList";
    }

    @Override
    public void validate(NotesDto notesDto, Model model) {
        LocalDate dateOfBirth = this.convertToLocalDate(notesDto.getDateOfBirth());
        int age = this.calculateAge(dateOfBirth, currentDate);
        notesDto.setAge(age);
        Notes notes = modelMapper.map(notesDto, Notes.class);
        notes.setId(generateSequence.generateSequence(Notes.SEQUENCE_NAME));
        notesRepository.save(notes);
        model.addAttribute("notesDto", notesRepository.findAll());
    }

    @Override
    public String deleteNote(Long id, Model model, Notes notes) {
        notesRepository.delete(notes);
        model.addAttribute("notesDto", notesRepository.findAll());
        return "notesDtoList";
    }

    public LocalDate convertToLocalDate(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    public int calculateAge(LocalDate localDate, LocalDate currentDate) {

        if ((localDate != null) && (currentDate != null)) {
            return Period.between(localDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public List<Notes> updatepatient(Long id, Notes notes) {
        List<Notes> notesList = notesRepository.findByLastNameAndFirstNameAndDateOfBirth(notes.getLastName(), notes.getFirstName(), notes.getDateOfBirth());
        return notesList;
    }

}

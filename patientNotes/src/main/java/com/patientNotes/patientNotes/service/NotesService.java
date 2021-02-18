package com.patientNotes.patientNotes.service;

import com.patientNotes.patientNotes.dto.NotesDto;
import com.patientNotes.patientNotes.model.Notes;
import com.patientNotes.patientNotes.repository.NotesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Service
public class NotesService implements INotesService {

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
    public void showNotesById(Long id, Model model, NotesDto notesDto) {
        Notes notes = notesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        notesDto = modelMapper.map(notes, NotesDto.class);
        model.addAttribute("notesDto", notesDto);
    }

    @Override
    public String updateNotes(Long id, NotesDto notesDto, Model model) {
        Notes notes = modelMapper.map(notesDto, Notes.class);
        notesRepository.save(notes);
        model.addAttribute("notesDto", notesRepository.findAll());
        return "notesDtoList";
    }

    @Override
    public void validate(NotesDto notesDto, Model model) {
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
}

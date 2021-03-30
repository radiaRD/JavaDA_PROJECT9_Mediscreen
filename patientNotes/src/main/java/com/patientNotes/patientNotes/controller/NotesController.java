package com.patientNotes.patientNotes.controller;

import com.patientNotes.patientNotes.dto.NotesDto;
import com.patientNotes.patientNotes.model.Notes;
import com.patientNotes.patientNotes.repository.NotesRepository;
import com.patientNotes.patientNotes.service.INotesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class NotesController {
    private static final Logger logger = LogManager.getLogger(NotesController.class);

    @Autowired
    INotesService notesService;

    @Autowired
    NotesRepository notesRepository;

    @RequestMapping("/notes/list")
    public String home(Model model, NotesDto notes) {
        logger.info("Get notes List");
        notesService.home(model, notes);
        return "notesDtoList";
    }

    @GetMapping("/notes/patient")
    public String getNotesPatient(NotesDto notes) {
        return "patientNotes";
    }

    @RequestMapping("/notes/listPatient")
    public String getListNotes(@RequestParam(value = "lastName") String lastName, @RequestParam(value = "firstName") String firstName, @RequestParam(value = "dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth, Model model) {
        logger.info("Get notes patient by last name = " + lastName+ ", first name = "+firstName+" and date of birth = "+ dateOfBirth);
        notesService.getListNotes(model, lastName, firstName, dateOfBirth);
        return "notesDtoList";
    }


    @GetMapping("/notes/update/{id}")
    public String showNotesById(@PathVariable("id") Long id, Model model, NotesDto notesDto) {
        logger.info("Show note by id = "+ id);
        notesService.showNotesById(id, model, notesDto);
        return "notesDto";
    }

    @PostMapping("/notes/update/{id}")
    public String updateNotes(@PathVariable("id") Long id, @Valid NotesDto notesDto,
                              BindingResult result, Model model) {

        if (!result.hasErrors()) {
            logger.info("Validate update note with id = " +id);
            return notesService.updateNotes(id, notesDto, model);
        }
        logger.info("Invalid update note with id = " +id);
        return "notesDto";
    }

    @GetMapping("/notes/add")
    public String addNotes(NotesDto notes) {
        logger.info("Add note");
        return "notesDtoAdd";
    }

    @PostMapping("/notes/validate")
    public String validate(@Valid NotesDto notesDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            logger.info("Validate add note");
            notesService.validate(notesDto, model);
            return "redirect:/notes/list";
        }
        logger.info("Invalid add note");
        return "notesDtoAdd";
    }

    @GetMapping("/notes/delete/{id}")
    public String deleteNotes(@PathVariable("id") Long id, Model model, Notes notes) {
        logger.info("Delete note with id = "+ id);
        notesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + id));
        return notesService.deleteNote(id, model, notes);
    }
}

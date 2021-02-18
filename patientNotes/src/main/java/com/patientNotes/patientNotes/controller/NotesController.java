package com.patientNotes.patientNotes.controller;

import com.patientNotes.patientNotes.dto.NotesDto;
import com.patientNotes.patientNotes.model.Notes;
import com.patientNotes.patientNotes.service.INotesService;
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
public class NotesController {

    @Autowired
    INotesService notesService;

    @RequestMapping("/notes/list")
    public String home(Model model, NotesDto notes) {
        notesService.home(model, notes);
        return "notesDtoList";
    }
    @GetMapping("/notes/update/{id}")
    public String showNotesById(@PathVariable("id") Long id, Model model, NotesDto notesDto) {
        notesService.showNotesById(id, model, notesDto);
        return "notesDto";
    }
    @PostMapping("/notes/update/{id}")
    public String updateNotes(@PathVariable("id") Long id, @Valid NotesDto notesDto,
                                BindingResult result, Model model) {

        if (!result.hasErrors()) {
            return notesService.updateNotes(id, notesDto, model);
        }
        return "notesDto";
    }

    @GetMapping("/notes/add")
    public String addNotes(NotesDto notes) {
        return "notesDtoAdd";
    }

    @PostMapping("/notes/validate")
    public String validate(@Valid NotesDto notesDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            notesService.validate(notesDto, model);
            return "redirect:/notes/list";
        }
        return "notesDtoAdd";
    }

    @GetMapping("/notes/delete/{id}")
    public String deleteNotes(@PathVariable("id") Long id, Model model,Notes notes) {
        return notesService.deleteNote(id, model, notes);
    }
}

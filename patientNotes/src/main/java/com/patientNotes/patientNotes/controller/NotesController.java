package com.patientNotes.patientNotes.controller;

import com.patientNotes.patientNotes.dto.NotesDto;
import com.patientNotes.patientNotes.model.Notes;
import com.patientNotes.patientNotes.service.INotesService;
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

    @Autowired
    INotesService notesService;

    @RequestMapping("/notes/list")
    public String home(Model model, NotesDto notes) {
        notesService.home(model, notes);
        return "notesDtoList";
    }

    @GetMapping("/notes/patient")
    public String getNotesPatient(NotesDto notes) {
        return "patientNotes";
    }

    @RequestMapping("/notes/listPatient")
    public String getListNotes(@RequestParam(value = "lastName") String lastName , @RequestParam(value ="firstName") String firstName,  @RequestParam(value ="dateOfBirth")@DateTimeFormat(pattern="yyyy-MM-dd")Date dateOfBirth, Model model) {
          notesService.getListNotes(model,lastName,firstName,dateOfBirth);
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

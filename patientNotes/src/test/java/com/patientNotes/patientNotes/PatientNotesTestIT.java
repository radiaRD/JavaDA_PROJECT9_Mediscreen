package com.patientNotes.patientNotes;

import com.patientNotes.patientNotes.model.Notes;
import com.patientNotes.patientNotes.repository.NotesRepository;
import com.patientNotes.patientNotes.service.SequenceGeneratorService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
<<<<<<< HEAD
=======
import org.springframework.test.context.ActiveProfiles;
>>>>>>> develop
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
<<<<<<< HEAD
=======
@ActiveProfiles("test")
>>>>>>> develop
public class PatientNotesTestIT {

    SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dateOfBirth = null;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    SequenceGeneratorService generateSequence;

    @BeforeEach
    public void cleanList() {
        notesRepository.deleteAll();
    }

    @Test
    void homeTest() throws Exception {
        this.mockMvc.perform(get("/notes/list")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("notesDtoList"))
                .andExpect(model().attribute("notesDto", Matchers.hasSize(0)));
    }

    @Test
    void getNotesPatientTest() throws Exception {
        dateOfBirth = textFormat.parse("1968-06-22");
        Notes notes = new Notes("Ferguson", "Lucas", dateOfBirth, "note");
        notes.setId(generateSequence.generateSequence(Notes.SEQUENCE_NAME));
        notes.setAge(52);
        notes = notesRepository.save(notes);
        dateOfBirth = textFormat.parse("1952-09-27");
        Notes notes1 = new Notes("Rees", "Pippa", dateOfBirth, "note");
        notes.setId(generateSequence.generateSequence(Notes.SEQUENCE_NAME));
        notes.setAge(68);
        notes = notesRepository.save(notes1);
        this.mockMvc.perform(post("/notes/listPatient?lastName=Ferguson&firstName=Lucas&dateOfBirth=1968-6-22").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("notesDtoList"))
                .andExpect(model().attribute("notesDto", Matchers.hasSize(1)));
    }

    @Test
    void showUpdateFormNotesTest() throws Exception {
        dateOfBirth = textFormat.parse("1968-06-22");
        Notes notes = new Notes("Ferguson", "Lucas", dateOfBirth, "note");
        notes.setId(generateSequence.generateSequence(Notes.SEQUENCE_NAME));
        notes.setAge(52);
        notes = notesRepository.save(notes);
        Long id = notes.getId();
        this.mockMvc.perform(get("/notes/update/" + id)).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("notesDto"))
                .andExpect(model().attribute("notesDto", Matchers.hasProperty("lastName", is("Ferguson"))));
    }

    @Test
    void updateNotesTest() throws Exception {
        dateOfBirth = textFormat.parse("1968-06-22");
        Notes notes = new Notes("Ferguson", "Lucas", dateOfBirth, "note");
        notes.setId(generateSequence.generateSequence(Notes.SEQUENCE_NAME));
        notes.setAge(52);
        notes = notesRepository.save(notes);
        Long id = notes.getId();
        this.mockMvc.perform(post("/notes/update/" + id)
                .param("lastName", "ferguson")
                .param("firstName", "Lucas")
                .param("dateOfBirth", "1968-06-22")
                .param("note", "update note")
                .contentType("text/html;charset=UTF-8"));
        this.mockMvc.perform(get("/notes/update/" + id)).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("notesDto"))
                .andExpect(model().attribute("notesDto", Matchers.hasProperty("note", is("update note"))));
    }

    @Test
    public void addNotesTest() throws Exception {
        this.mockMvc.perform(get("/notes/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("notesDtoAdd"));
    }

    @Test
    public void validateTest() throws Exception {
        this.mockMvc.perform(post("/notes/validate")
                .param("lastName", "ferguson")
                .param("firstName", "Lucas")
                .param("dateOfBirth", "1968-06-22")
                .param("note", "note")
                .contentType("text/html;charset=UTF-8"));
        this.mockMvc.perform(get("/notes/list")).andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("notesDto", Matchers.hasSize(1)));
    }

    @Test
    void deleteNotesTest() throws Exception {
        dateOfBirth = textFormat.parse("1968-06-22");
        Notes notes = new Notes("Ferguson", "Lucas", dateOfBirth, "note");
        notes.setId(generateSequence.generateSequence(Notes.SEQUENCE_NAME));
        notes.setAge(52);
        notes = notesRepository.save(notes);
        Long id = notes.getId();
        this.mockMvc.perform(get("/notes/delete/" + id)).andDo(print());
        this.mockMvc.perform(get("/notes/list")).andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("notesDto", Matchers.hasSize(0)));
    }

}

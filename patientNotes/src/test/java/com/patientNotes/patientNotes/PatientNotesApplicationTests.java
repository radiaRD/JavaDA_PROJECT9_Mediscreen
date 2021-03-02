package com.patientNotes.patientNotes;

import com.patientNotes.patientNotes.model.Notes;
import com.patientNotes.patientNotes.repository.NotesRepository;
import com.patientNotes.patientNotes.service.NotesService;
import com.patientNotes.patientNotes.service.SequenceGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.junit.Assert;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
class PatientNotesApplicationTests {

    SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dateOfBirth = null;

    @Autowired
    NotesService notesService;
    @Autowired
    NotesRepository notesRepository;
    @Autowired
    SequenceGeneratorService generateSequence;

    @BeforeEach
    public void cleanList() {
        notesRepository.deleteAll();
    }

    @Test
    void calculateAgeTest() {
        LocalDate birthDate = LocalDate.of(1966, 06, 22);
        LocalDate currentDate = LocalDate.now();
        int actual = notesService.calculateAge(birthDate, LocalDate.of(2021, 02, 18));
        Assert.assertEquals(54, actual);
    }

    @Test
    public void getNotesPatientTest() throws ParseException {
        Date dateOfBirth = textFormat.parse("1968-06-22");
        Notes notes = new Notes("Ferguson", "Lucas", dateOfBirth, "note");
        notes.setId(generateSequence.generateSequence(Notes.SEQUENCE_NAME));
        notes.setAge(52);
        notes = notesRepository.save(notes);
        List<Notes> listResult = notesRepository.findAll();
        Assert.assertEquals(52, notes.getAge());
        Assert.assertTrue(listResult.size() > 0);
    }

    @Test
    public void updateNotesPatientTest() throws ParseException {
        Date dateOfBirth = textFormat.parse("1968-06-22");
        Notes notes = new Notes("Ferguson", "Lucas", dateOfBirth, "note");
        notes.setId(generateSequence.generateSequence(Notes.SEQUENCE_NAME));
        notes.setAge(52);
        notes = notesRepository.save(notes);
        notes.setLastName("Fergus");
        notes = notesRepository.save(notes);
        Assert.assertTrue(notes.getLastName().equals("Fergus"));
    }

    @Test
    public void addPatientNotesTest() throws ParseException {
        Date dateOfBirth = textFormat.parse("1968-06-22");
        Notes notes = new Notes("Ferguson", "Lucas", dateOfBirth, "note");
        notes.setId(generateSequence.generateSequence(Notes.SEQUENCE_NAME));
        notes.setAge(52);
        notes = notesRepository.save(notes);
        Assert.assertNotNull(notes.getId());
        Assert.assertTrue(notes.getLastName().equals("Ferguson"));
    }

    @Test
    public void deletePatientTest() throws ParseException {
        Date dateOfBirth = textFormat.parse("1968-06-22");
        Notes notes = new Notes("Ferguson", "Lucas", dateOfBirth, "note");
        notes.setId(generateSequence.generateSequence(Notes.SEQUENCE_NAME));
        notes.setAge(52);
        notes = notesRepository.save(notes);
        Long id = notes.getId();
        notesRepository.delete(notes);
        Optional<Notes> patientdelete = notesRepository.findById(id);
        Assert.assertFalse(patientdelete.isPresent());
    }
}

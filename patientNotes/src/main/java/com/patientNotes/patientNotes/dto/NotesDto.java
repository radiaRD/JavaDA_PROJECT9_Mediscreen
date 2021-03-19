package com.patientNotes.patientNotes.dto;


import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;



public class NotesDto {
    @Id
    private long id;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotNull(message = "Date of birth is mandatory")
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private int age;
    private LocalDate updateNoteDate = LocalDate.now();
    private String note;

    public NotesDto() {
    }

    public NotesDto(String lastName, String firstName, Date dateOfBirth, String note) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return updateNoteDate;
    }

    public void setDate(LocalDate updateNoteDate) {
        this.updateNoteDate = updateNoteDate;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", note='" + note + '\'' +
                '}';
    }
}

package com.patientNotes.patientNotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.annotation.Transient;


import java.io.Serializable;
import java.util.Date;

@Document(collection = "notes")
public class Notes implements Serializable {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;
    private String lastName;
    private String firstName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private int age;
    private LocalDateTime updateNoteDate;
    private String note;

    public Notes() {
    }

    public Notes(String lastName, String firstName, Date dateOfBirth, String note) {
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

    public LocalDateTime getDate() {
        return updateNoteDate;
    }

    public void setDate(LocalDateTime updateNoteDate) {
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

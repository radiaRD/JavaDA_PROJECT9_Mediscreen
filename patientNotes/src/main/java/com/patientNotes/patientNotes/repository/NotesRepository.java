package com.patientNotes.patientNotes.repository;

import com.patientNotes.patientNotes.model.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends MongoRepository<Notes, Long> {
}

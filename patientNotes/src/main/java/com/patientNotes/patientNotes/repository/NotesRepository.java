package com.patientNotes.patientNotes.repository;

import com.patientNotes.patientNotes.model.Notes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends MongoRepository<Notes, Long> {
    static final Logger logger = LogManager.getLogger(NotesRepository.class);

    List<Notes> findByLastNameAndFirstNameAndDateOfBirth(String lastName, String firstName, Date dateOfBirth);

    Optional<Notes> findFirstByLastName(String lastName);

    Optional<Notes> findFirstByFirstName(String firstName);

    Optional<Notes> findFirstByDateOfBirth(Date DateOfBirth);
}

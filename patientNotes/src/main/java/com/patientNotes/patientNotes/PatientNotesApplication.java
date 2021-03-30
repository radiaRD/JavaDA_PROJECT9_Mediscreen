package com.patientNotes.patientNotes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PatientNotesApplication {
    private static final Logger logger = LogManager.getLogger(PatientNotesApplication.class);

    public static void main(String[] args) {
        logger.info("Initializing Application");
        SpringApplication.run(PatientNotesApplication.class, args);
    }

}

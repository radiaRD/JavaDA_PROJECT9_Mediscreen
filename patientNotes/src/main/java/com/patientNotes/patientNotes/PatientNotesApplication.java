package com.patientNotes.patientNotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
public class PatientNotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientNotesApplication.class, args);
	}

}

package com.patientData.patientInformation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PatientInformationApplication {
    private static final Logger logger = LogManager.getLogger(PatientInformationApplication.class);

    public static void main(String[] args) {
        logger.info("Initializing Application");
        SpringApplication.run(PatientInformationApplication.class, args);
    }

}

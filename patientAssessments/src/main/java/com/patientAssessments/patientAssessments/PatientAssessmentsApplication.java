package com.patientAssessments.patientAssessments;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.patientAssessments")
public class PatientAssessmentsApplication {
    private static final Logger logger = LogManager.getLogger(PatientAssessmentsApplication.class);

    public static void main(String[] args) {
        logger.info("Initializing Application");
        SpringApplication.run(PatientAssessmentsApplication.class, args);
    }

}

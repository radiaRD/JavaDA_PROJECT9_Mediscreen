package com.patientAssessments.patientAssessments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.patientAssessments")
public class PatientAssessmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientAssessmentsApplication.class, args);
	}

}

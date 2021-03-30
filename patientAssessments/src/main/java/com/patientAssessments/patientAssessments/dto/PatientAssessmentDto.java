package com.patientAssessments.patientAssessments.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PatientAssessmentDto {
    private static final Logger logger = LogManager.getLogger(PatientAssessmentDto.class);

    private String lastName;
    private String firstName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String sex;
    private String homeAddress;
    private String phoneNumber;
    private String riskLevel;

    public PatientAssessmentDto() {
    }

    public PatientAssessmentDto(String lastName, String firstName, Date dateOfBirth, String sex, String homeAddress, String phoneNumber, String riskLevel) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.riskLevel = riskLevel;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    @Override
    public String toString() {
        return "PatientAssessmentDto{" +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", sex='" + sex + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", riskLevel='" + riskLevel + '\'' +
                '}';
    }
}

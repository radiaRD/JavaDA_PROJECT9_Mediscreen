package com.patientData.patientInformation.dto;


import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

import java.util.Date;


public class PatientDto {

    private Integer id;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotNull(message = "Date of birth is mandatory")
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @NotNull(message = "Sex is mandatory")
    private String sex;
    private String homeAddress;
    //    @UniqueElements
    private String phoneNumber;

    public PatientDto() {
    }

    public PatientDto(@NotBlank(message = "Username is mandatory") String lastName, @NotBlank(message = "First Name is mandatory") String firstName, @NotBlank(message = " Date of birth is mandatory") Date dateOfBirth, @NotBlank(message = "Sex is mandatory") String sex, String homeAddress, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}

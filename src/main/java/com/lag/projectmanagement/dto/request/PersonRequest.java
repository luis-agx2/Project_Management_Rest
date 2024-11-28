package com.lag.projectmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PersonRequest {
    @NotBlank
    @Length(min = 3, max = 20)
    private String firstName;

    @Length(min = 3, max = 25)
    @NotBlank
    private String lastName;

    private LocalDate birthDate;

    private Byte gender;

    private String phoneNumber;

    private Set<Long> roles = new HashSet<>();

    public PersonRequest() {
    }

    public PersonRequest(
            String firstName,
            String lastName,
            LocalDate birthDate,
            Byte gender,
            String phoneNumber,
            Set<Long> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Long> getRoles() {
        return roles;
    }

    public void setRoles(Set<Long> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Person[" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roles=" + roles +
                ']';
    }
}

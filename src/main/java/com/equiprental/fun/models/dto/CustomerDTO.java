package com.equiprental.fun.models.dto;

import com.equiprental.fun.util.Gender;
import com.equiprental.fun.util.Regex;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CustomerDTO implements Serializable {

    @NotBlank(message = "First name must not be blank")
    @Pattern(regexp = Regex.NAME_SURNAME, message = "First name contains invalid character.")
    private String firstName;
    @NotBlank(message = "Last name must not be blank")
    @Pattern(regexp = Regex.NAME_SURNAME, message = "Last name contains invalid character.")
    private String lastName;
    @NotBlank(message = "CNP must not be blank")
    @Pattern(regexp = Regex.CNP, message = "Invalid CNP format")
    private String CNP;
    @NotBlank(message = "Email must not be blank")
    @Email(regexp = Regex.EMAIL, message = "Email is not valid")
    private String email;
    @NotBlank(message = "Phone number must not be blank")
    @Pattern(regexp = Regex.PHONE_NUMBER, message = "Phone number must be a 10-digit number")
    private String phoneNumber;
    @NotNull(message = "Date of birth must not be null")
    @Past(message = "Date of birth must be in the past")
    @Pattern(regexp = Regex.DATE, message = "Invalid values in date of birth field.")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender must not be null")
    private Gender gender;
    @NotBlank(message = "Street must not be blank.")
    @Pattern(regexp = Regex.STREET, message = "Invalid values in the street address field.")
    private String street;
    @NotBlank(message = "Building number must not be blank.")
    @Pattern(regexp = Regex.BUILDING_NUMBER, message = "Invalid values in the building number field.")
    private String buildingNumber;
    @NotBlank(message = "City must not be blank.")
    @Pattern(regexp = Regex.CITY, message = "Invalid values for city field")
    private String city;
}
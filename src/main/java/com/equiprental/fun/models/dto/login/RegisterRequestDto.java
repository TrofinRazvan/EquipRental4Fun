package com.equiprental.fun.models.dto.login;

import com.equiprental.fun.util.Gender;
import com.equiprental.fun.util.Regex;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRequestDto implements Serializable {

    @Email(regexp = Regex.EMAIL, message = "Email is not valid")
    private String email;

    @NotBlank
    private String password;

    @NotBlank(message = "First name must not be blank")
    @Pattern(regexp = Regex.NAME_SURNAME, message = "First name contains invalid character.")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    @Pattern(regexp = Regex.NAME_SURNAME, message = "Last name contains invalid character.")
    private String lastName;

    @NotBlank(message = "City must not be blank.")
    @Pattern(regexp = Regex.CITY, message = "Invalid values for city field")
    private String city;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender must not be null")
    private Gender gender;
}
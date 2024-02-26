package com.equiprental.fun.models.dto.login;

import com.equiprental.fun.util.Regex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequestDto implements Serializable {

    @Email(regexp = Regex.EMAIL, message = "Email is not valid")
    private String email;

    @NotBlank
    private String password;
}
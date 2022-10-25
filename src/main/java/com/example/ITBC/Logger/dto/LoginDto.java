package com.example.ITBC.Logger.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotEmpty
    @Size(min = 3,message = "username at least 3 characters")
    private String username;
    @NotEmpty
    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z])+)(?=(.*\\d)+)(?=(.*\\W)+)(?!.*\\s).{8,}$",
            message = "password at least 8 characters and one letter and one number and one character")
    private String password;
}

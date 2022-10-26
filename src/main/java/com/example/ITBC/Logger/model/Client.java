package com.example.ITBC.Logger.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    @NotEmpty
    @Size(min = 3,message = "username at least 3 characters")
    private String username;
    @NotEmpty
    @Email(message = "email must be valid")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z])+)(?=(.*\\d)+)(?=(.*\\W)+)(?!.*\\s).{8,}$",
    message = "password at least 8 characters and one letter and one number and one character")
    private String password;
    private UserType userType = UserType.USER;
    private Integer logCount = 0;
}

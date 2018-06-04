package com.example.demo.model.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {

    @Email(message = "Email format is incorrect")
    @Length(min = 3, max = 255, message = "Email length should be between 3 and 255 characters")
    @NotBlank(message = "Email cannot be blank")
    private String email;
}

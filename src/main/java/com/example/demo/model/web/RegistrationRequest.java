package com.example.demo.model.web;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegistrationRequest {

    @NotBlank(message = "First name cannot be blank")
    @Length(min = 3, max = 255, message = "First name should be between 3 and 255 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Length(min = 3, max = 255, message = "Last name length should be between 3 and 255 characters")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email format is incorrect")
    @Length(min = 3, max = 255, message = "Email length should be between 3 and 255 characters")
    private String email;

}

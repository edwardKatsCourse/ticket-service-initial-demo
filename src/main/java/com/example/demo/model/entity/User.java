package com.example.demo.model.entity;

import com.example.demo.model.entity.converters.UserTypeConverter;
import com.example.demo.model.entity.converters.UserType;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    @Convert(converter = UserTypeConverter.class)
    private UserType userType;
}
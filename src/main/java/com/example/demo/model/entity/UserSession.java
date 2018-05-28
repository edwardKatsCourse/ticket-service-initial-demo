package com.example.demo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String sessionId;

    @ManyToOne
    private User user;

    private LocalDateTime createdOn;

    private Boolean isExpired;
}

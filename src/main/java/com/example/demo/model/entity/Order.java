package com.example.demo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User boughtBy;

    @ManyToOne
    private User soldBy;

    private LocalDateTime purchaseDate;
    private Double price;



}

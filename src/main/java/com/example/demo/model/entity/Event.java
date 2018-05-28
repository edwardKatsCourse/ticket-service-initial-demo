package com.example.demo.model.entity;

import com.example.demo.model.entity.converters.EventStatusConverter;
import com.example.demo.model.entity.converters.EventStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Hall hall;

    private String eventName;

    private LocalDateTime eventStart;

    private LocalDateTime eventEnd;

    @Convert(converter = EventStatusConverter.class)
    private EventStatus eventStatus;
}

package com.example.demo.model.web;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EventRequest {

    private String eventName;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;

    private Integer durationInHours;

    private Integer hallId;
}

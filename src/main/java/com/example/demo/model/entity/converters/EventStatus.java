package com.example.demo.model.entity.converters;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EventStatus {

    SCHEDULED(1),
    ENDED(2),
    CANCELLED(3);

    private Integer id;

    public Integer getId() {
        return id;
    }

    public static EventStatus getById(Integer id) {
        if (id == null) {
            return null;
        }

        for (EventStatus eventStatus : values()) {
            if (eventStatus.getId().equals(id)) {
                return eventStatus;
            }
        }

        return null;

        /*
        return Arrays.stream(values())
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
                */
    }
}

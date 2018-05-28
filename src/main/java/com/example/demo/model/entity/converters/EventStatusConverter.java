package com.example.demo.model.entity.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EventStatusConverter implements AttributeConverter<EventStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EventStatus eventStatus) {
        return eventStatus == null ? null : eventStatus.getId();
    }

    @Override
    public EventStatus convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : EventStatus.getById(dbData);
    }
}

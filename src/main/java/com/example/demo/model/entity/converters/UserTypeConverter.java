package com.example.demo.model.entity.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserType attribute) {
        return attribute == null ? null : attribute.getId();
    }

    @Override
    public UserType convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : UserType.getById(dbData);
    }
}

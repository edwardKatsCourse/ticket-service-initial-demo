package com.example.demo.model.entity.converters;

import com.example.demo.model.entity.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserRole attribute) {
        return attribute == null ? UserRole.GUEST.getId() : attribute.getId();
    }

    @Override
    public UserRole convertToEntityAttribute(Integer dbData) {
        return UserRole.getById(dbData);
    }
}

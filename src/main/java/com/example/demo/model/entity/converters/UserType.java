package com.example.demo.model.entity.converters;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * Jackson = fasterxml
 */
@AllArgsConstructor
public enum UserType {
    REGULAR(1),
    ADMIN(2),
    SUPERUSER(3)
    ;

    @JsonValue // - как показывать твой ENUM в JSON
    public Integer getId() {
        return id;
    }


    private Integer id;

    /**
     *
     * Когда в JSON поле userType придет Integer или String или another object,
     * Jackson пойдет искать @JsonCreator
     */
    @JsonCreator
    public static UserType getById(Integer id) {
        if (id == null) {
            return null;
        }

        return Arrays.stream(values())
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

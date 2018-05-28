package com.example.demo.model.entity.converters;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum UserType {
    REGULAR(1)


    ;

    private Integer id;


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

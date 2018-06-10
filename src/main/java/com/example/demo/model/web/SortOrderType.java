package com.example.demo.model.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum SortOrderType {
    ASCENDING(1),
    DESCENDING(2)

    ;

    private Integer id;


    @JsonCreator
    public static SortOrderType getByTypeId(Integer typeId) {
        if (typeId == null) {
            return SortOrderType.ASCENDING;
        }

        return Arrays.stream(values())
                .filter(x -> x.getId().equals(typeId))
                .findFirst()
                .orElse(SortOrderType.ASCENDING);
    }

}

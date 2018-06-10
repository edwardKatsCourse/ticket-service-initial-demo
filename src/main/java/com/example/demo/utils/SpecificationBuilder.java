package com.example.demo.utils;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SpecificationBuilder {

    public static Specification build(List<Specification> specifications) {
        if (specifications == null || specifications.isEmpty()) {
            return null;
        }

        //firstName, lastName, age
        Specification result = specifications.get(0); //firstName
        for (int i = 0; i < specifications.size(); i++) {
                                   //firstName and lastName
                           //firstName, lastName and age

            result = Specification.where(result).and(specifications.get(i));
        }

        //firstName, lastName, age

        return result;
    }
}

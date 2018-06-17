package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    GUEST(1, "ROLE_GUEST"),
    REGULAR(2, "ROLE_REGULAR"),
    ADMIN(3, "ROLE_ADMIN")
    ;

    private Integer id;
    private String roleName;

    public static UserRole getById(Integer id) {
        if (id == null) {
            return GUEST;
        }

        for (UserRole userRole : values()) {
            if (userRole.getId().equals(id)) {
                return userRole;
            }
        }

        return GUEST;
    }

}

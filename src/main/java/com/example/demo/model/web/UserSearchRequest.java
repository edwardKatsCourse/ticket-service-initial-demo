package com.example.demo.model.web;

import com.example.demo.model.entity.converters.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserSearchRequest {

    //      {}

    private String firstName;
    private String lastName;
    private String email;
    private UserType userType; //Integer
    private String middleName;


    public boolean hasParams() {
        return !StringUtils.isEmpty(this.firstName) || //this.firstName == null || this.firstName.isEmpty()
                !StringUtils.isEmpty(this.lastName) ||
                !StringUtils.isEmpty(this.email) ||
                !StringUtils.isEmpty(this.middleName) ||
                this.userType != null;
    }
}

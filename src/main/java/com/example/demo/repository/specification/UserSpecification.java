package com.example.demo.repository.specification;

import com.example.demo.model.entity.User;
import com.example.demo.model.entity.converters.UserType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class UserSpecification {

    //(where) firstName == 'first name'
    public static Specification<User> findByFirstName(String firstName) {
        return new Specification<User>() {
            //:firstName
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("firstName"), firstName);
            }
        };
        //                                                            ==  u.firstName            :firstName
        //return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("firstName"), firstName));
    }

    //AND..OR

    public static Specification<User> findByLastName(String lastName) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lastName"), lastName));
    }


    public static Specification<User> findByEmail(String email) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email));
    }


    public static Specification<User> findByUserType(UserType userType) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userType"), userType));
    }

    public static Specification<User> findByMiddleName(String middleName) {
        return ((root, query, criteriaBuilder) -> {
            Path<Object> middleNamePath = root.get("middleName");
            return criteriaBuilder.equal(middleNamePath, middleName);
        });
    }

    public static Specification<User> findByMiddleNameNotEmpty() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.isNotNull(
                root.get("middleName")
        ));
    }

}
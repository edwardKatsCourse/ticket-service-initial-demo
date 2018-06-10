package com.example.demo.repository.specification;

import com.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserSearchSpecificationRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

//    User findByLastNameOrFirstName(String a, String b);
//
//
//    @Query(value = "from User u where u.email = :email")
//    User getMyCustomUser(@Param("email") String a);
}

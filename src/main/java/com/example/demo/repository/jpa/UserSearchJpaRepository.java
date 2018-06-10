package com.example.demo.repository.jpa;

import com.example.demo.model.entity.User;
import com.example.demo.model.web.UserSearchRequest;

import java.util.List;

public interface UserSearchJpaRepository {

    List<User> findAll(UserSearchRequest userSearchRequest, Integer page, Integer size);
}

package com.example.demo.service;

import com.example.demo.model.entity.User;
import com.example.demo.model.web.SortOrderType;
import com.example.demo.model.web.UserSearchRequest;

import java.util.List;

public interface UserSearchService {
    List<User> findUsers(UserSearchRequest userSearchRequest, Integer page, Integer size, SortOrderType sortOrderType);

    List<User> findByMiddleNameNotEmpty();
}

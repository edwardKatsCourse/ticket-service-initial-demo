package com.example.demo.controller;

import com.example.demo.model.entity.User;
import com.example.demo.model.web.SortOrderType;
import com.example.demo.model.web.UserSearchRequest;
import com.example.demo.repository.jpa.UserSearchJpaRepository;
import com.example.demo.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserSearchController {


    @Autowired
    private UserSearchJpaRepository userSearchJpaRepository;

    @Autowired
    private UserSearchService userSearchService;

    /**
     *
     * 100 users
     *
     * 0 page (1 страница) 10 results -> 1 - 10 users
     *
     * 0 page 20 results -> (100 / 20 -> 5 pages total) 1 .. 20
     * 1 page 20 results -> (100 / 20 -> 5 pages total) 21 .. 40
     * 2 page 20 results -> (100 / 20 -> 5 pages total) 41 .. 60
     * 3 page 20 results -> (100 / 20 -> 5 pages total) 61 .. 80
     * 4 page 20 results -> (100 / 20 -> 5 pages total) 81 .. 100
     *
     */

    @PostMapping("/jpa/search")
    public List<User> findUsersJpa(@RequestBody UserSearchRequest userSearchRequest,
                                @RequestParam(value = "page") Integer page,
                                @RequestParam(value = "size") Integer size,
                                @RequestParam(value = "order", required = false) SortOrderType order) {


        return userSearchJpaRepository.findAll(userSearchRequest, page, size);
    }

    @PostMapping("/specifications/search")
    public List<User> findUsersSpecification(@RequestBody UserSearchRequest userSearchRequest,
                                             @RequestParam(value = "page") Integer page,
                                             @RequestParam(value = "size") Integer size,
                                             @RequestParam(value = "order", required = false) SortOrderType order) {

        return userSearchService.findUsers(userSearchRequest, page, size, order);
    }


    @GetMapping("/specifications/has_middle-name")
    public List<User> findByMiddleNameNotEmpty() {
        return userSearchService.findByMiddleNameNotEmpty();
    }


}

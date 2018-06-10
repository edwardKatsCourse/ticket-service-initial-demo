package com.example.demo.service.impl;

import com.example.demo.model.entity.User;
import com.example.demo.model.web.SortOrderType;
import com.example.demo.model.web.UserSearchRequest;
import com.example.demo.repository.specification.UserSpecification;
import com.example.demo.repository.specification.UserSearchSpecificationRepository;
import com.example.demo.service.UserSearchService;
import com.example.demo.utils.SpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSearchServiceImpl implements UserSearchService {

    @Autowired
    private UserSearchSpecificationRepository userSearchSpecificationRepository;

    @Override
    public List<User> findUsers(UserSearchRequest userSearchRequest, Integer page, Integer size, SortOrderType sortOrderType) {


        if (!userSearchRequest.hasParams()) {
            return userSearchSpecificationRepository.findAll();
        }

        List<Specification> specifications = new ArrayList<>();

        if (!StringUtils.isEmpty(userSearchRequest.getFirstName())) {
            specifications.add(UserSpecification.findByFirstName(userSearchRequest.getFirstName()));
        }

        if (!StringUtils.isEmpty(userSearchRequest.getLastName())) {
            specifications.add(UserSpecification.findByLastName(userSearchRequest.getLastName()));
        }

        if (!StringUtils.isEmpty(userSearchRequest.getEmail())) {
            specifications.add(UserSpecification.findByEmail(userSearchRequest.getEmail()));
        }

        if (!StringUtils.isEmpty(userSearchRequest.getUserType())) {
            specifications.add(UserSpecification.findByUserType(userSearchRequest.getUserType()));
        }

        if (!StringUtils.isEmpty(userSearchRequest.getMiddleName())) {
            specifications.add(UserSpecification.findByMiddleName(userSearchRequest.getMiddleName()));
        }

        //SELECT * FROM USER        WHERE             FIRST_NAME = "PETER"       AND        LAST_NAME = "JIMMIE"     AND            AGE >= 18
        //       SPRING DATA        specs (start)       SPECIFICATION           spec API       SPECIFICATION       spec API      SPECIFICATION
        Specification result = SpecificationBuilder.build(specifications);

        return userSearchSpecificationRepository.findAll(result);
    }


    @Override
    public List<User> findByMiddleNameNotEmpty() {
        Specification result = UserSpecification.findByMiddleNameNotEmpty();

        return userSearchSpecificationRepository.findAll(result);
    }
}

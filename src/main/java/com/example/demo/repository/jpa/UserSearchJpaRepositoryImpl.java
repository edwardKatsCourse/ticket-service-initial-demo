package com.example.demo.repository.jpa;

import com.example.demo.model.entity.User;
import com.example.demo.model.web.UserSearchRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserSearchJpaRepositoryImpl implements UserSearchJpaRepository {

    private static final String AND_KEYWORD = "AND ";

    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * HQL - Hibernate Query Language
     * JPQL - Java Persistence Query Language
     *
     * copy/paste
     */

    @Override
    public List<User> findAll(UserSearchRequest userSearchRequest,
                              Integer page,
                              Integer size) {

        //firstName = null
        //lastName= null
        //email= null
        //userType = 1
        //select from User where 1 = 1
        //                              and firstName = "asdasd"
        //                              and lastName = "asdasd"

        StringBuilder builder = new StringBuilder();
        builder.append("select u from User u ");

        if (userSearchRequest.hasParams()) {
            builder.append("where 1 = 1 ");
        }

        Map<String, Object> parameters = new HashMap<>();
        if (!StringUtils.isEmpty(userSearchRequest.getFirstName())) {
            builder.append(AND_KEYWORD);
            builder.append(" u.firstName = :firstName ");
            parameters.put("firstName", userSearchRequest.getFirstName());
        }


        if (!StringUtils.isEmpty(userSearchRequest.getLastName())) {
            builder.append(AND_KEYWORD);
            builder.append(" u.lastName = :lastName ");
            parameters.put("lastName", userSearchRequest.getLastName());
        }

        if (!StringUtils.isEmpty(userSearchRequest.getEmail())) {
            builder.append(AND_KEYWORD);
            builder.append(" u.email= :email ");
            parameters.put("email", userSearchRequest.getEmail());
        }

        if (userSearchRequest.getUserType() != null) {
            builder.append(AND_KEYWORD);
            builder.append(" u.userType = :userType ");
            parameters.put("userType", userSearchRequest.getUserType());
        }

        if (userSearchRequest.getMiddleName() != null) {
            builder.append(AND_KEYWORD);
            builder.append(" u.middleName = :middleName");
            parameters.put("middleName", userSearchRequest.getMiddleName());
        }

        Query query = entityManager.createQuery(builder.toString());

        parameters.entrySet()
                .forEach(x -> query.setParameter(x.getKey(), x.getValue()));


        //100 users
        //0 page
        //10 results per page

        //4 page
        //10 results per page

        return query.setMaxResults(size) //10
                .setFirstResult((page -1) * size) //0, according to the documentation, zero returns from the beginning!
                .getResultList();
    }
}

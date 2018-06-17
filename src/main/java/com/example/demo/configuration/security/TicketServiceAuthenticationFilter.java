package com.example.demo.configuration.security;

import com.example.demo.model.entity.UserSession;
import com.example.demo.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TicketServiceAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {


        String header = httpServletRequest.getHeader("Authorization");
        if (header == null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        UserSession userSession = userSessionRepository.findBySessionIdAndIsExpiredFalse(header);
        if (userSession == null || userSession.getIsExpired()) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        /**
         * Spring Security allows to cascade roles: a user may have multiple roles, like ROLE_GUEST, ROLE_ADMIN, ROLE_REGULAR
         */
        String role = userSession.getUser().getUserRole().getRoleName(); //ROLE_ADMIN

        //Spring Security's interface for roles is GrantedAuthority -> SimpleGrantedAuthority(String roleName)
        List<GrantedAuthority> grantedAuthorities = Stream
                .of(role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userSession.getUser().getEmail(),
                null,
                grantedAuthorities
        );

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

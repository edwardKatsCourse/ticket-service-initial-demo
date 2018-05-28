package com.example.demo.configuration.security;

import com.example.demo.model.entity.UserSession;
import com.example.demo.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

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

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userSession.getUser().getEmail(),
                null,
                Collections.emptyList()
        );

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

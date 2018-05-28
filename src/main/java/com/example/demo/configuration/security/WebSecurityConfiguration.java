package com.example.demo.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import java.util.stream.Stream;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private TicketServiceAuthenticationFilter myFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] permittedWithoutAuthorization = Stream
                .of(
                        "/users/registration",
                        "/users/login",
                        "/events/both")
                .toArray(String[]::new);

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(permittedWithoutAuthorization).permitAll()
                .anyRequest().authenticated();


        http.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

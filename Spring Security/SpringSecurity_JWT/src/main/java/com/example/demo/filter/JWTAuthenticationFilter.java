package com.example.demo.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


import com.example.demo.service.TokenAuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("Run JWTAuthenticationFilter.doFilter");

        Authentication authentication = TokenAuthenticationService
                .getAuthentication((HttpServletRequest) servletRequest);

        System.out.println("Complete Authenticate");

        if (Objects.nonNull(authentication) && !StringUtils.isEmpty(authentication.getPrincipal())) {
            System.out.println("Successfully Authenticate: " + authentication.getPrincipal());
        } else {
            System.out.println("Unsuccessfully Authenticate with Token: " + ((HttpServletRequest) servletRequest).getHeader(TokenAuthenticationService.HEADER_STRING));
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
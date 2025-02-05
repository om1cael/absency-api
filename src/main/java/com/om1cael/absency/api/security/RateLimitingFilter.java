package com.om1cael.absency.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    @Autowired
    private RateLimiting rateLimiting;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(rateLimiting.tryConsume()) {
            filterChain.doFilter(request, response);
            return;
        }

        response.setContentType("application/json");
        response.setStatus(429);
        response.getWriter().append("{\"success\": false, \"message\": \"Too many requests\"}");
    }
}

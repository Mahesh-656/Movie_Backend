package com.movie.movieRest.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {

    public JwtFilter() {
        System.out.println("JwtFilter");
    }


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
private ApplicationContext applicationContext;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String email = null;

        // 🔍 Extract JWT from cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JWT".equals(cookie.getName())) {
                    jwtToken = cookie.getValue();
                    break;
                }
            }
        }

        if (jwtToken != null) {
            email = jwtUtil.extractUsername(jwtToken);
        }
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            jwtToken = authHeader.substring(7);
//            email = jwtUtil.extractUsername(jwtToken);

            if (email!= null&&SecurityContextHolder.getContext().getAuthentication()==null) {
                UserDetails userDetails = applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(email);
                if (jwtUtil.validateToken(jwtToken, userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        filterChain.doFilter(request, response);
    }
}

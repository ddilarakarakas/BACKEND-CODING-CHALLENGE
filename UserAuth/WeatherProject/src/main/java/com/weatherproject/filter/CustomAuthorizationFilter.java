package com.weatherproject.filter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.weatherproject.filter.CustomAuthenticationFilter.APPLICATION_JSON_VALUE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private void checkToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain,
                            String authorizationHeader, String startsWith) throws ServletException, IOException{
        log.info(authorizationHeader);
        if (authorizationHeader != null && authorizationHeader.startsWith(startsWith)){
            try {
                log.info("Check authorization token");
                String token = authorizationHeader.substring(startsWith.length());
                log.info("token : {}", token);
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                log.info("algorithm : {}", algorithm);
                JWTVerifier verifier = JWT.require(algorithm).build();
                log.info("verifier : {}", verifier);
                DecodedJWT decodedJWT = verifier.verify(token);
                log.info("decodedJWT : {}", decodedJWT);
                String username = decodedJWT.getSubject();
                log.info("username : {}", username);
                String role = decodedJWT.getClaim("role").asString();
                log.info("role : {}", role);
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(role));
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                log.info("Set auth token : {}", authenticationToken);
                filterChain.doFilter(request, response);
                log.info("Finish checking token");
            }catch (Exception exception){
                log.error("Error logging in : {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                //response.sendError(HttpServletResponse.SC_FORBIDDEN);
                Map<String, String > error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        else {
            log.info("Authorization header null or token didnt start {}", startsWith);
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/api/login") || request.getServletPath().equals("/weather")){
            filterChain.doFilter(request, response);
        } else {

            checkToken(request, response, filterChain, request.getHeader(AUTHORIZATION), "Bearer ");
        }
    }

    /*@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getServletPath().startsWith("/api/login")) {
            log.info("if : " + request.getServletPath());
            filterChain.doFilter(request, response);
        }
        else if (request.getServletPath().startsWith("/api")){
            log.info("Else if: " + request.getServletPath());
            checkToken(request,response,filterChain,request.getHeader(AUTHORIZATION), "Bearer ");
        } else{
            log.info("Else : " + request.getServletPath());
            filterChain.doFilter(request, response);
        }
    }*/
}

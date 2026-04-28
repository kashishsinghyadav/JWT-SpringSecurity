package org.kashish.jwtspringsecurity.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kashish.jwtspringsecurity.service.JwtService;
import org.kashish.jwtspringsecurity.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.context.ApplicationContext;


import java.io.IOException;
@Service
public class jwtFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtService jwtservice;

    @Autowired
    private  ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Bearer jwt token
        String authheader = request.getHeader("Authorization"); // get the authorization token
        String token = null ;
        String Username = null;

        if(authheader != null && authheader.startsWith("Bearer ")){
            token = authheader.substring(7);
            Username = jwtservice.extractUserName(token);

        }

        if(Username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(Username);

            if(jwtservice.validateToekn(token,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);


            }
        }
        filterChain.doFilter(request,response);





    }

}
